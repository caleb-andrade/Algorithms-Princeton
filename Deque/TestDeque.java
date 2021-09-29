/*------------------------------------------------
 * Author:          Caleb Andrade
 * Created:         8/July/2015
 * Last modified:   9/July/2015
 * 
 * Client class unit test for Deque implementation 
 *------------------------------------------------*/

public class TestDeque {
      
    // test isEmpty method
    public void testIsEmpty() {
        Deque<String> deque1 = new Deque<String>();
        if (deque1.isEmpty()) StdOut.println("\ntest isEmpty #1 ok!");
        Deque<Integer> deque2 = new Deque<Integer>();
        if (deque2.isEmpty()) StdOut.println("test isEmpty #2 ok!");
        Deque<Double> deque3 = new Deque<Double>();
        if (deque3.isEmpty()) StdOut.println("test isEmpty #3 ok!");
    }  
    
    // test size method
    public void testSize() {
        Deque<String> deque = new Deque<String>();
        if (deque.size() == 0) StdOut.println("\ntest size #1 ok!");
        deque.addFirst("a");
        if (deque.size() == 1) StdOut.println("test size #2 ok!");
        deque.addFirst("b");
        if (deque.size() == 2) StdOut.println("test size #3 ok!");
    }
        
    // test addFirst and removeFirst
    public void testAddFirstRemoveFirst() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.addFirst("e");
        deque.addFirst("f");
        deque.addFirst("g");
        deque.addFirst("h");
        deque.addFirst("i");
        deque.addFirst("j");
        if (deque.size() == 10) StdOut.println("\ntest AddFirstRemoveFirst #1 ok!");
        String word = "";
        for (int i = 0; i < 10; i++) {
            word = word + deque.removeFirst();
        }
        if (word.equals("jihgfedcba")) StdOut.println("test AddFirstRemoveFirst #2 ok!"); 
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.removeFirst();
        deque.addFirst("d");
        deque.addFirst("e");
        deque.removeFirst();
        deque.addFirst("f");
        deque.addFirst("g");
        deque.addFirst("h");
        deque.removeFirst();
        deque.addFirst("i");
        deque.addFirst("j");
        word = "";
        for (int i = 0; i < 7; i++) {
            word = word + deque.removeFirst();
        }
        if (word.equals("jigfdba")) StdOut.println("test AddFirstRemoveFirst #3 ok!");
    }
    
    // test addFirst and RemoveLast
    public void testAddFirstRemoveLast() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.addFirst("e");
        deque.addFirst("f");
        deque.addFirst("g");
        deque.addFirst("h");
        deque.addFirst("i");
        deque.addFirst("j");
        String word = "";
        for (int i = 0; i < 10; i++) {
            word = word + deque.removeLast();
        }
        if (word.equals("abcdefghij")) StdOut.println("\ntest AddFirstRemoveLast #1 ok!"); 
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.removeLast();
        deque.addFirst("d");
        deque.addFirst("e");
        deque.removeLast();
        deque.addFirst("f");
        deque.addFirst("g");
        deque.addFirst("h");
        deque.removeLast();
        deque.addFirst("i");
        deque.addFirst("j");
        word = "";
        for (int i = 0; i < 7; i++) {
            word = word + deque.removeLast();
        }
        if (word.equals("defghij")) StdOut.println("test AddFirstRemoveLast #2 ok!");
        if (deque.isEmpty()) StdOut.println("test AddFirstRemoveLast #3 ok!");
    }
    
    // test addLast and removeFirst
    public void testAddLastRemoveFirst() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.addLast("i");
        deque.addLast("j");
        if (deque.size() == 10) StdOut.println("\ntest AddLastRemoveFirst #1 ok!");
        String word = "";
        for (int i = 0; i < 10; i++) {
            word = word + deque.removeFirst();
        }
        if (word.equals("abcdefghij")) StdOut.println("test AddLastRemoveFirst #2 ok!"); 
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.removeFirst();
        deque.addLast("d");
        deque.addLast("e");
        deque.removeFirst();
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.removeFirst();
        deque.addLast("i");
        deque.addLast("j");
        word = "";
        for (int i = 0; i < 7; i++) {
            word = word + deque.removeFirst();
        }
        if (word.equals("defghij")) StdOut.println("test AddLastRemoveFirst #3 ok!");
    }
    
    // test addLast and RemoveLast
    public void testAddLastRemoveLast() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.addLast("i");
        deque.addLast("j");
        String word = "";
        for (int i = 0; i < 10; i++) {
            word = word + deque.removeLast();
        }
        if (word.equals("jihgfedcba")) StdOut.println("\ntest AddLastRemoveLast #1 ok!"); 
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.removeLast();
        deque.addLast("d");
        deque.addLast("e");
        deque.removeLast();
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.removeLast();
        deque.addLast("i");
        deque.addLast("j");
        word = "";
        for (int i = 0; i < 7; i++) {
            word = word + deque.removeLast();
        }
        if (word.equals("jigfdba")) StdOut.println("test AddLastRemoveLast #2 ok!");
        else                        StdOut.println("word: " + word);
        if (deque.isEmpty()) StdOut.println("test AddLastRemoveLast #3 ok!");
    }
    
    // test iterator
    public void testIterator() {
        StdOut.println("\ntest #1 single iterator");
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.addLast("i");
        deque.addLast("j");
        String word = "";
        for (String s: deque) word = word + s + " ";
        StdOut.println(word);
        StdOut.println("\ntest #2 multiple iterator ");
        for (String x: deque) {
            word = "";
            for (String y: deque) word = word + x + y + " ";
            StdOut.println(word);
        }
    }
}