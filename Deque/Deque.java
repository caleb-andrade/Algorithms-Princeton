/* -----------------------------------------------------------------------
 * Author:            Caleb Andrade
 * First modified:    8/July/2015
 * Last modified:     9/July/2015
 * 
 * Dequeue implementation. A generalization of a stack and a queue that 
 * supports adding and removing items from either the front or the back of 
 * the data structure.
 * ----------------------------------------------------------------------- */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N; // size of dequeue
    private Node first, last; // front and end of dequeue
    
    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }
    
    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Null element"); 
        Node oldfirst = first; // rename first as oldfirst
        first = new Node(); // create new node, name it first
        first.item = item; // put item in first
        //first.prev = null; // is this code necessary?
        if (isEmpty()) last = first; 
        else {
            first.next = oldfirst; // link nodes properly
            oldfirst.prev = first; 
        }
        N++;
    }
    
    // add item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Null element");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        //last.next = null; // is this code necessary?
        if (isEmpty()) first = last;
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item; // save item to return it
        first = first.next; // reassign first
        if (isEmpty()) last = null; // erase last
        else           first.prev = null; // erase reference to previous first
        N--;
        return item; 
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.prev;
        if (isEmpty()) first = null; // erase first
        else           last.next = null; // erase reference to previous last
        N--;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new ListIterator(); }
    
    // This fragment of code ownership's belongs to R. Sedgewick and K. Wayne
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove()     { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (!hasNext()) throw new  NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
        
    /*// unit testing
    public static void main(String[] args) {
        StdOut.println("\nUnit testing");
        TestDeque test = new TestDeque();
        test.testIsEmpty();
        test.testSize();
        test.testAddFirstRemoveFirst();
        test.testAddFirstRemoveLast();
        test.testAddLastRemoveFirst();
        test.testAddLastRemoveLast();
        test.testIterator();
    } */
    
}        
        
        
        
        
        
        
    
        
 