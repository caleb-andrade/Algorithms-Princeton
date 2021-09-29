/*----------------------------------------------------------------    
 * Author:        Caleb Andrade
 * Written:       1/July/2015
 * Last updated:  5/July/2015
 * 
 * Compilation:   javac Percolation.java
 * Execution:     java Percolation
 * 
 * Runs a percolation simulation applying union-find data structure
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;      // declare an array of arrays for grid
    private WeightedQuickUnionUF gridUF; // declare union-find data structure
    private WeightedQuickUnionUF gridUFC; 
    
    // create N-by-N grid with all sites blocked and a union-find data type
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Illegal value for N");
        grid = new boolean[N][N]; // initialize grid with False entries
                            
        gridUF = new WeightedQuickUnionUF(N*N + 2);
        gridUFC = new WeightedQuickUnionUF(N*N + 2);
    }
            
    // open site (i, j) if not open
    public void open(int i, int j) {
        validate(i, j);
        int N = grid[0].length;
        if (!grid[i - 1][j - 1]) {
            grid[i - 1][j - 1] = true; 
            openNeighbor(i, j);
            if (i == 1) {
                gridUF.union(0, j); 
                gridUFC.union(0, j);
            }
            if (i == N) gridUF.union(N*N + 1, (i - 1)*N + j);
        }
    }
      
    // is site (i, j) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i - 1][j - 1];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return isOpen(i, j) && gridUFC.connected(0, map1D(i, j));
    }
    
    // does system percolate?
    public boolean percolates() {
        int N = grid[0].length;
        if (N > 1) { return gridUF.connected(0, N*N + 1); }
        else       { return isOpen(1, 1); }
    }
        
    // check (i, j) are valid indexes
    private void validate(int i, int j) {
        int N = grid[0].length;          
        if (i <= 0 || i > N) {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        if (j <= 0 || j > N) {
            throw new IndexOutOfBoundsException("column index out of bounds");
        }
    }
    
    // maps (i, j) to a unique 1D value
    private int map1D(int i, int j) {
        int N = grid[0].length;
        return (i - 1)*N + j;
    }
    
    // connects (i, j) with proper open neighbors
    private void openNeighbor(int i, int j) {
        int N = grid[0].length;
        int fix = map1D(i, j);
        int neighbor;
        if (i < N && isOpen(i + 1, j)) {
            neighbor = map1D(i + 1, j);
            gridUF.union(fix, neighbor);
            gridUFC.union(fix, neighbor);
        }
        if (i > 1 && isOpen(i - 1, j)) {
            neighbor = map1D(i - 1, j);
            gridUF.union(fix, neighbor);
            gridUFC.union(fix, neighbor);
        }
        if (j < N && isOpen(i, j + 1)) {
            neighbor = map1D(i, j + 1);
            gridUF.union(fix, neighbor);
            gridUFC.union(fix, neighbor);
        }
        if (j > 1 && isOpen(i, j - 1)) {
            neighbor = map1D(i, j - 1);
            gridUF.union(fix, neighbor);
            gridUFC.union(fix, neighbor);
        }
    }
}
  
        
    
        
        
        



