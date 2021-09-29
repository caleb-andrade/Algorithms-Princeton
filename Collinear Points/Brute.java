/*************************************************************************
 * Name:           Caleb Andrade
 * Email:          casernas@ams.sunysb.edu
 * Created:        15/July/2015
 * Last modified:  15/July/2015
 *
 * Description: A brute force algorithm to check collinearities among 4 pts
 *************************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Brute {
    
    // checks if four points are collinear
    private static boolean check(Point i, Point j, Point k, Point l) {
        return (i.slopeTo(j) == i.slopeTo(k) && i.slopeTo(k) == i.slopeTo(l));
    }
    
    // reads input, tests collinearity, prints and draws output
    public static void main(String[] args) {
        // set plotting variables
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        List<Point> a = new ArrayList<Point>(); // create array to store pts
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            a.add(p); // add point to list
        }
        
        // loop on every combination of four points
        List<Point> b = new ArrayList<Point>(); // create temp array
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        if (check(a.get(i), a.get(j), a.get(k), a.get(l))) {
                            b.add(a.get(i));
                            b.add(a.get(j));
                            b.add(a.get(k));
                            b.add(a.get(l));
                            Collections.sort(b);
                            b.get(0).drawTo(b.get(3));                            
                            StdOut.println(b.get(0)+" -> "+b.get(1)+" -> "+b.get(2)+" -> "+b.get(3));
                        }
                        b.clear();                         
                    }
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);
    }
}   
