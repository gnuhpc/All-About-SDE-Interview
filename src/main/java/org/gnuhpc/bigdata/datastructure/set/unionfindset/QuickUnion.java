package org.gnuhpc.bigdata.datastructure.set.unionfindset;

/*
lazy approach
Data structure.
Integer array  id[] of size  N .
Interpretation: id[i] is parent of  i .
Root of  i is id[id[id[...id[i]...]]] .

Find. Check if p and q have the same root.
Union. Set the id of q's root to the id of p's root.

Quick-union defect.
    Trees can get tall.
    Find too expensive (could be N steps)
    Need to do find to do union

Quick Union is Faster than Quick Find
 */


public class QuickUnion implements IQuickUnion {

    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    //O(N) worst
    public int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    //O(N) worst
    // root of p is root of j; p belongs to q's connected component group
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        id[pid] = qid;
    }

}
