/*----------------------------------------------------------------------
 * Author:         Caleb Andrade
 * Created:        Aug/23/2015
 * Last modified:  Aug/24/2015
 * 
 * Brute-force implementation. 
 * Write a mutable data type PointSET.java that represents a set of points 
 * in the unit square. Implement the following API by using a red-black BST 
 * (using either SET from algs4.jar or java.util.TreeSet).
 -----------------------------------------------------------------------*/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
//import edu.princeton.cs.algs4.StdOut;

public class PointSET {
    private SET<Point2D> points;
    
    // construct an empty set of points
    public PointSET() { 
        points  = new SET<Point2D>(); 
    }
    
    // is the set empty?
    public boolean isEmpty() { 
        return points.isEmpty(); 
    }
    
    // number of points in the set
    public int size() { 
        return points.size(); 
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) { 
        if (p == null) throw new NullPointerException("Null point");
        points.add(p); 
    }
    
    // does the set contain point p?
    public boolean contains(Point2D p) { 
        if (p == null) throw new NullPointerException("Null point");
        return points.contains(p); 
    }
    
    // draw all points to standard draw
    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }
    
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("Null rectangle");
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
               queue.enqueue(p);
            }
        }
        return queue;
    }
     
    // a nearest neighbor to the point p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("Null point");
        Point2D neighbor = null;
        if (points.isEmpty()) { return neighbor; }
        else {
            double dist = Double.POSITIVE_INFINITY;
            for (Point2D q : points) {
                if (p.distanceSquaredTo(q) < dist) {
                    dist = p.distanceSquaredTo(q);
                    neighbor = q;
                }
            }
        }
        return neighbor;
    }
}
  
