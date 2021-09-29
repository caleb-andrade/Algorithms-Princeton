
/*--------------------------------------------------------------
 * Author:          Caleb Andrade
 * Created:         9/July/2015
 * Last modified:   9/July/2015
 * 
 * Subset client. Takes an input of N string elements. Returns
 * k of them, choosing randomly and uniform from the input
 * -------------------------------------------------------------*/

public class Subset {
    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}

  