import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionPathCompressionUF {
    private int[] id;
    private int[] sz;
    private int count;
    public QuickUnionPathCompressionUF(int N) {
        count = N;
        id = new int[N];
        for (int i=0;i<N;i++) id[i] = i;
        sz = new int[N];
        for (int i=0;i<N;i++) sz[i] = 1;
    }
    public int count() {
        return count;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (sz[i] < sz[j]) { 
            id[i] = j; sz[j] += sz[i];
        }
        else { 
            id[j] = i; sz[i] += sz[j];
        }
        count--;
    }
    
        public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}