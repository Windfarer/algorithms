import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class ResizingCapacityStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];  //create new array first
        for (int i=0; i<N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    public void push(Item item) {
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }
    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N>0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    public Iterator<Item> iterator(){ 
        return new ReverseArrayIterator(); 
    }
      
    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() {
            return i > 0;
        }
        public Item next() {
            return a[--i]; //reverse
        }
        public void remove() {}
    }
}