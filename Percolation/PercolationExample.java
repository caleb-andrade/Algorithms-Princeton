import java.util.Random;

public class PercolationExample {
    private static Percolation test;
    private static Random rand;
    
    public static void main(String[] args) {
        System.out.println("Enter a grid value for N: ");
        int N = StdIn.readInt();
        rand = new Random();
        test = new Percolation(N);
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (rand.nextBoolean()) {
                    test.open(i, j);
                }
            }
        }
        System.out.println("Percolates? :" + test.percolates());
    }
}
    