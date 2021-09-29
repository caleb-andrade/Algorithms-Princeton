/*---------------------------------------------------------------------------
 * Author:        Caleb Andrade
 * Created:       30/July/2015
 * Last Modified: 30/July/2015
 * ------------------------------------------------------------------------*/
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private final Stack<Board> solution = new Stack<Board>();
    private int moves;
    private Board initialBoard;
        
    // Define an inner class for nodes (boards)
    private class Node implements Comparable<Node> {
        private Board board;
        private Node previous;
        private int moves;
        
        Node(Board board, int moves, Node previous) {
            this.board =  board;
            this.moves = moves;
            this.previous = previous;
        }
        
        private int priority() {
            // using manhattan priority function
            return this.board.manhattan() + this.moves; 
            
            // using hamming priority function
            // return this.board.hamming() + this.moves;
        }
        
        public int compareTo(Node that) {
        if (this.priority() < that.priority()) { return -1; }
        if (this.priority() > that.priority()) { return 1; }
        return 0;
        }
    } 
         
    public Solver(Board initial) {
    
        if (initial == null) throw new NullPointerException("Null element");
        initialBoard = initial;
        // initialize PQ and create initial Node
        MinPQ<Node> queue = new MinPQ<Node>();
        Node iniNode = new Node(initial, 0, null);
        Node twinNode = new Node(initial.twin(), 0, null);
        queue.insert(iniNode);
        queue.insert(twinNode);
        Node proNode;
        
        while (!queue.isEmpty()) {
            proNode = queue.delMin();
            moves = proNode.moves;
           if (proNode.board.isGoal()) { 
                while (proNode.previous != null) {
                    solution.push(proNode.board);
                     proNode = proNode.previous;
                }
                solution.push(proNode.board);
                break;
            }
            
            for (Board item : proNode.board.neighbors()) {
                if (proNode.previous == null || !item.equals(proNode.previous.board)) {
                    queue.insert(new Node(item, moves + 1, proNode));
                }
            }    
        }
    }
    
    public boolean isSolvable() { return  initialBoard.equals(solution.peek()); }
   
    public int moves() { 
        if (isSolvable()) { return moves; }
        else            { return -1; }
    }
     
    public Iterable<Board> solution() { 
        if (isSolvable()) { return solution; }
        else            { return null; }
    }
        
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("\nMinimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
      
        
   
        
            
            
 
        