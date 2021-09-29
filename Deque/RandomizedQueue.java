/*---------------------------------------------------------------------
 * Author:             Caleb Andrade
 * Created:            9/July/2015
 * Last Modified:      9/July/2015
 * 
 * Implementation of a randomized queue, the item removed is chosen 
 * uniformly at random from items in the data structure.
 *---------------------------------------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;  // array of items
    private int N;         // number of elements on array
    
    // Initializes an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[2]; // the ugly cast!
    }
    
    // Is this randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items in the randomized queue
    public int size() {
        return N;
    }
    
    // this following fragment of code is due to R. Sedgewick and K. Wayne
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity]; // another cast!
        for (int i = 0; i < N; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Null item");
        if (N == array.length) resize(2*array.length); // double size of array
        array[N++] = item;
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty Queue");
        int ran = StdRandom.uniform(N);
        Item item = array[ran];
        array[ran] = array[N-1];
        array[N-1] = null;
        N--;
        if (N > 0 && N == array.length / 4) resize(array.length / 2);
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample() { 
        if (isEmpty()) throw new NoSuchElementException("Empty Queue");
        return array[StdRandom.uniform(N)]; 
    }
    
    // this code fragment is a modified version of R.S. and K.W. implementation
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] temp;
        
        public RandomArrayIterator() {
            i = N-1;
            temp = (Item[]) new Object[N]; // ugly cast again!
            for (int i = 0; i < N; i++) {
                temp[i] = array[i];
            }
            StdRandom.shuffle(temp);            
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return temp[i--];
        }
    }
    
    // Unit tests the RandomizedQueue data type.
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        queue.enqueue("g");
        queue.enqueue("h");
        queue.enqueue("i");
        queue.enqueue("j");
        StdOut.println("\nTesting sample() ");
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println("\nTesting iterator ");
        for (String x: queue) StdOut.println(x);
        StdOut.println("\nTesting multiple iterator ");
        String word;
        for (String x: queue) {
            word = "";
            for (String y: queue) {
                word = word + x + y + " ";
            }
            StdOut.println(word);
        }
        int temp = queue.size();
        StdOut.println("\nTesting dequeue(), queue has " + temp + " items");
        for (int i = 0; i < temp; i++) {
            StdOut.println(queue.dequeue()); 
        }
        StdOut.println("\nQueue has " + queue.size() + " items");
                      
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
        
        
        
        
    
    
        
    
    
    
    
    
    
    
        