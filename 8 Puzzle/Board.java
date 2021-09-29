/*----------------------------------------------------------------------------
 * Author: Caleb Andrade
 * Date created: 29/July/2015
 * Last modified: 30/July/2015
 * 
 * 8 Puzzle Board class immutable data type implementation
 *--------------------------------------------------------------------------*/
import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int[] board;
    private int[] zero;
    
    // construct a board from an N-by-N array of blocks
    public Board(int[][] blocks) {
        N = blocks[0].length;
        board = new int[N*N];
        for (int i = 0; i < N*N; i++) {
            board[i] = blocks[i / N][i % N];
            if (board[i] == 0) { 
                zero = new int[] { i / N, i % N };
            }
        }
    }
                  
    // board dimension N
    public int dimension() { return N; }
    
    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N*N; i++) {
            if (board[i] - 1 != i && board[i] != 0) { count++; }
        }
        return count;
    }
        
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N*N; i++) {
            if (board[i] != 0) {
            count += Math.abs((board[i] - 1) / N - i / N); 
            count += Math.abs((board[i] - 1) % N - i % N);
            }
        }
        return count;
    }
        
    // boolean isGoal()
    public boolean isGoal() { return hamming() == 0; }
    
    // get block method
    private int getBlock(int i) {
        return board[i];
    }
    
    // helper function, creates a copy of board swapping two of its entries
    private int[][] swapNew(int i, int j, int x, int y) {
        int[][] ans = new int[N][N];
        for (int n = 0; n < N*N; n++) { 
            ans[n / N][n % N] = board[n]; 
        }
        ans[i][j] = board[N*x + y];
        ans[x][y] = board[N*i + j];
        return ans;
    }
       
    // a board that is obtained by swapping two adjacent blocks in the same row
    public Board twin() {
        int idx = 0;
        for (int i = 0; i < N*N; i++) {
            if (i % N == 0 && board[i] != 0 && board[i + 1] != 0) { 
                idx = i;
                break; }
        }
        Board twinBoard = new Board(swapNew(idx / N, 0, idx / N, 1));
        return twinBoard;
    }
                
    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true; 
        if (y == null) return false; 
        if (y.getClass() != this.getClass()) return false; 
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false; 
        for (int i = 0; i < N*N; i++) {
            if (this.getBlock(i) != that.getBlock(i)) { return false; }
        }
        return true;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<Board>();
        Board neigh;
        // upper neighbor
        if (zero[0] > 0) { 
            neigh = new Board(swapNew(zero[0], zero[1], zero[0] - 1, zero[1]));
            queue.enqueue(neigh);
        } 
        // bottom neighbor
        if (zero[0] < N - 1) {
            neigh = new Board(swapNew(zero[0], zero[1], zero[0] + 1, zero[1]));
            queue.enqueue(neigh);
        }
        // left neighbor
        if (zero[1] > 0) { 
            neigh = new Board(swapNew(zero[0], zero[1], zero[0], zero[1] - 1));
            queue.enqueue(neigh);
        }
        // right neighbor
        if (zero[1] < N - 1) {
            neigh = new Board(swapNew(zero[0], zero[1], zero[0], zero[1] + 1));
            queue.enqueue(neigh);
        }
        return queue;
    }
    
    // string representation of this board (method provided by R.S. and K.W.)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N*N; i++) {
            s.append(String.format("%2d ", board[i]));
            if (i % N == N - 1) { s.append("\n"); }
        }
        return s.toString();
    }
            
    // testing unit
    public static void main(String[] args) {
        int D = 4;
        int[][] test = new int[D][D];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                test[i][j] = 1 + j + D*i;
            }
        }
        test[0][D - 1] = 1;
        test[1][1] = 0;
        test[D - 1][D - 1] = D;
        test[0][0] = D + 2;
        Board game = new Board(test);
        StdOut.println("\ngame board: \n" + game);
        StdOut.println("dimension: " + game.dimension());
        StdOut.println("hamming: " + game.hamming());
        StdOut.println("manhattan: " + game.manhattan());
        StdOut.println("is current board goal board? : " + game.isGoal());
        StdOut.println("is equal board and twin?: " + game.equals(game.twin()));
        StdOut.println("\ntwin: \n" + game.twin());
        Board game2 = new Board(test);
        StdOut.println("is equal board to a copy?: " + game.equals(game2));
        StdOut.println("is equal copy to board?: " + game2.equals(game));
        StdOut.println("neighbors: ");
        for (Board neighbor : game.neighbors()) {
            StdOut.println("\n" + neighbor);
        }
                       
    }
}
        