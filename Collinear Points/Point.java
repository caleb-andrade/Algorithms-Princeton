/*************************************************************************
 * Name:         Caleb Andrade
 * Email:        casernas@ams.sunysb.edu
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double slope1 = slopeTo(p);
            double slope2 = slopeTo(q);
            if (slope1 > slope2) { return 1; }
            if (slope1 < slope2) { return -1; }            
            return 0;
        }
    }
            
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        int dy = this.y - that.y;
        int dx = this.x - that.x;
        if (dy != 0 && dx == 0) { return Double.POSITIVE_INFINITY; }
        if (dy == 0 && dx == 0) { return Double.NEGATIVE_INFINITY; }
        if (dy == 0 && dx != 0) { return 0.0; }
        return (double) dy / dx;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y)                          { return 1; }
        else if (this.y == that.y && this.x > that.x) { return 1; }
        else if (this.y == that.y && this.x == that.x) { return 0; }
        return -1;
    }
    
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}