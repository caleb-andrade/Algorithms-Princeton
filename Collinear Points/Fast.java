/*************************************************************************
 * Name:           Caleb Andrade
 * Email:          casernas@ams.sunysb.edu
 * Created:        15/July/2015
 * Last modified:  15/July/2015
 *
 * Description: A faster algorithm to check collinearities among 4 or more pts
 *************************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Fast {
    
    // format printing method
    private static String output(List<Point> a) {
        String ans = "";
        ans += a.get(0);
        for (int i = 1; i < a.size(); i++) { 
            ans += " -> " + a.get(i); 
        }
        return ans;
    }
    
    // checks if a segment has been drawn already, to avoid duplicates
    private static boolean check(List<String> t, String seg) {
        for (String item : t) {
            if (seg.equals(item)) { return true; }
            }
        return false;
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
        Point[] a = new Point[N]; // create array to store pts
        Point[] c = new Point[N]; // make a copy of points' list
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw(); // draw `point
            a[i] = p; // add point to list
            c[i] = p; // add point to list's copy
        }
        
        // for each point i, sort by slope
        if (N > 3) {
            List<Point> b = new ArrayList<Point>(); // create temp array
            List<String> t = new ArrayList<String>(); // list to store segments
            for (int i = 0; i < N; i++) {
                Point origin = c[i];
                Arrays.sort(a, 0, N,origin.SLOPE_ORDER);
                double temp = origin.slopeTo(a[1]); // first slope
                for (int j = 1; j < N; j++) {
                    double slope = origin.slopeTo(a[j]);
                    if (slope == temp) { b.add(a[j]); }
                    if (slope != temp || j == N - 1) {
                        int sz = b.size();
                        if (sz > 2) {
                            b.add(origin);
                            Collections.sort(b);
                            String seg = b.get(0).toString();
                            seg += b.get(sz).toString();
                            if (!check(t, seg)) {
                                t.add(seg);
                                b.get(0).drawTo(b.get(sz));
                                StdOut.println(output(b));
                            }
                        }
                        temp = slope;
                        b.clear();
                        b.add(a[j]);
                    }
                } 
                b.clear();
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}
          
