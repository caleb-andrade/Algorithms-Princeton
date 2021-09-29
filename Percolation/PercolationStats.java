/*----------------------------------------------------------------    
 * Author:        Caleb Andrade
 * Written:       3/July/2015
 * Last updated:  3/July/2015
 * 
 * Compilation:   javac PercolationStats.java
 * Execution:     java PercolationStats N T
 * 
 *Performs a series of computational experiments, Monte Carlo 
 * simulation, to approximate the threshold of percolation
 *----------------------------------------------------------------*/

public class PercolationStats {
    private static double[] openSites; 
    
    // perform T independent experiments on a N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0) throw new IllegalArgumentException("Illegal value for N");
        if (T <= 0) throw new IllegalArgumentException("Illegal value for T");
        
        openSites = new double[T]; // this array will store the results
        
        // Loop T times to perform a percolation simulation each time
        for (int k = 0; k < T; k++) {
            double count = 0; // keeps track of open sites
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j); 
                    count++;
                }
            }
            openSites[k] = count / (N*N);
        }
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSites);
    }
    
    // sample standard deviation of percolation threshold   
    public double stddev() {
        return StdStats.stddev(openSites);
    }
    
    // low  endpoint of 95% confidence interval     
    public double confidenceLo() {
        return mean() - (1.96*stddev() / Math.sqrt(openSites.length));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96*stddev() / Math.sqrt(openSites.length));
    }       
    
    // test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats experiment = new PercolationStats(N, T);
        System.out.println("mean                    = " + experiment.mean());
        System.out.println("stddev                  = " + experiment.stddev());
        double a = experiment.confidenceLo();
        double b = experiment.confidenceHi();
        System.out.println("95% confidence interval = " + a + ", " + b);
    }
}
        
            
                
            
                
            
            
            
        
        