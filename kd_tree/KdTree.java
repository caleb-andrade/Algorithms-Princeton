/*----------------------------------------------------------------------
 * Author:         Caleb Andrade
 * Created:        Aug/24/2015
 * Last modified:  Aug/28/2015
 * 
 * 2d-tree implementation. Write a mutable data type KdTree.java that uses 
 * a 2d-tree to implement the same API (but replace PointSET with KdTree). 
 * A 2d-tree is a generalization of a BST to two-dimensional keys. 
 * The idea is to build a BST with points in the nodes, using the x- and 
 * y-coordinates of the points as keys in strictly alternating sequence.
 -----------------------------------------------------------------------*/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private int N; // size of 2d-tree
    private Node root; // start node of 2d-tree
    private Stack<Node> stack; // stack to implement DFS for draw()
    private Queue<Point2D> queue; // queue for range
    private Point2D champ; // nearest neighbor
    private double best; // closest distance
    
    
    // define an inner class to represent a Node
    private class Node {
        private Point2D pt;  // the point
        private double[] rect;   // the coordinates that define the enclosing box
        private Node lb;     // the left/bottom subtree
        private Node rt;     // the right/top subtree
    }
    
    // initialize the 2d-tree
    public KdTree() { 
        root = new Node();
        N = 0;
    }
    
    // is the set empty?
    public boolean isEmpty() { 
        return root.pt == null; 
    }
    
    // number of points in the set
    public int size() { 
        return N;
    }
    
    // helper method that constructs the enclosing box of the point added
    private double[] box(double ac, double bc, Node b, boolean even) {
        double[] r = new double[4];
        for (int i = 0; i < 4; i++) { r[i] = b.rect[i]; }
        if (even && ac < bc)        { r[2] = b.pt.x(); }
        else if (even && ac >= bc)  { r[0] = b.pt.x(); }
        else if (!even && ac < bc)  { r[3] = b.pt.y(); }
        else                        { r[1] = b.pt.y(); }
        return r;
    }
          
     /* 
     * helper method to perform a search in the tree. The "even" argument
     * refers to the depth's parity, depending on that, the x or y coorinates 
     * will be compared. Returns the last node of the search. If flag is true,
     * then the node is added to the tree if it was not there.
     */
    private Node search(Point2D a, Node b, boolean even, boolean flag) {
        if (b == null || a.equals(b.pt)) return b;
        double ac, bc;
        if (even) { 
            ac = a.x(); 
            bc = b.pt.x(); 
        }
        else { 
            ac = a.y(); 
            bc = b.pt.y(); 
        }
        if (flag) {
            Node na = new Node();
            na.pt = a;
            na.rect = box(ac, bc, b, even);
            if (ac < bc && b.lb == null) { 
                b.lb = na; 
                return null; 
            }
            else if (ac >= bc && b.rt == null) { 
                b.rt = na; 
                return null; 
            }
        }
        if (ac < bc) { return search(a, b.lb, !even, flag); }
        else         { return search(a, b.rt, !even, flag); }
    }
           
    // add the node to the 2d-tree (if it is not already in it)
    public void insert(Point2D p) { 
        nulltest(p);
        if (root.pt == null) { 
            root.pt = p; 
            root.rect = new double[4]; 
            root.rect[2] = 1.0; 
            root.rect[3] = 1.0;
            N = 1;
        }
        else if (search(p, root, true, true) == null) { N++; }
    }
        
    // does the set contain point p?
    public boolean contains(Point2D p) { 
        nulltest(p);
        if (this.isEmpty()) return false;
        return search(p, root, true, false) != null;        
    }
    
    // helper function that recursively traverses the tree to perform range search
    private void rsearch(Node node, RectHV rect) {
        if (node == null) return;
        RectHV nr = new RectHV(node.rect[0], node.rect[1], 
                               node.rect[2], node.rect[3]);
        if (!rect.intersects(nr)) return;
        if (rect.contains(node.pt)) { queue.enqueue(node.pt); }
        rsearch(node.lb, rect);
        rsearch(node.rt, rect);
    }
        
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("Null rectangle");
        queue = new Queue<Point2D>();
        if (this.isEmpty()) return queue;
        rsearch(root, rect);
        return queue;
    }
    
    // helper function to determine which subtree to search first
    private void subsearch(Node n, Point2D q) {
        if (n.lb == null && n.rt == null) return;
        else if (n.lb != null && n.rt == null) { nsearch(n.lb, q); }
        else if (n.lb == null && n.rt != null) { nsearch(n.rt, q); }
        else if (n.lb != null && n.rt != null) {
            if ((q.x() >= n.lb.rect[0]) && (q.x() <= n.lb.rect[2])
            && (q.y() >= n.lb.rect[1]) && (q.y() <= n.lb.rect[3])) 
            {
                nsearch(n.lb, q);
                nsearch(n.rt, q);
            }
            else {
                nsearch(n.rt, q);
                nsearch(n.lb, q);
            }
        }
    }
    
    // this helper function, is taken from algorithms.cs RectHV class directly
    private double distanceSquaredTo(Point2D p, double[] r) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < r[0]) dx = p.x() - r[0];
        else if (p.x() > r[2]) dx = p.x() - r[2];
        if      (p.y() < r[1]) dy = p.y() - r[1];
        else if (p.y() > r[3]) dy = p.y() - r[3];
        return dx*dx + dy*dy;
    }
        
    // helper function, recursively traverses tree to find nearest neighbor
    private void nsearch(Node n, Point2D q) {
        if (n == null) return;
        if (distanceSquaredTo(q, n.rect) >= best) return; // pruning rule
        double dist = q.distanceSquaredTo(n.pt);
        if (dist < best) { 
            champ = n.pt; 
            best = dist; // new champ found, new best distance
        }
        subsearch(n, q);     
    }    
     
    // a nearest neighbor to the point p; null if set is empty
    public Point2D nearest(Point2D p) {
        nulltest(p);
        if (this.isEmpty()) return null; 
        best = Double.POSITIVE_INFINITY;
        nsearch(root, p);
        return champ; // champ and best are global variables within the class
    }
    
    /*************************************************************************/
    
    // helper method to explore tree and draw it
    private void explore(Node node, boolean even) {
        if (node == null) return; 
        stack.push(node);
        explore(node.lb, !even);
        explore(node.rt, !even);
        Node temp = stack.pop();
        StdDraw.setPenRadius(0.002);
        if (even) { 
            StdDraw.setPenColor(255, 0, 0);
            StdDraw.line(temp.pt.x(), temp.rect[1], temp.pt.x(), temp.rect[3]);
        }
        else {
            StdDraw.setPenColor(0, 0, 255);
            StdDraw.line(temp.rect[0], temp.pt.y(), temp.rect[2], temp.pt.y());
        }
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.point(temp.pt.x(), temp.pt.y());
    }
        
    // draw all points to standard draw
    public void draw() {
        stack = new Stack<Node>();
        explore(root, true);
    }
    
    // helper function that tests if argument is null
    private void nulltest(Point2D p) {
        if (p == null) throw new NullPointerException("Null point");
    } 
}
