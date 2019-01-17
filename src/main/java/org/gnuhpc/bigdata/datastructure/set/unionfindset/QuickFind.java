package org.gnuhpc.bigdata.datastructure.set.unionfindset;

// Eager solution
/*
Integer array  id[] of size  N .
Interpretation: p and  q are connected if they have the same id.

Find:
        Check if  p and  q have the same id.
Union:
        To merge components containing  p and  q,
        change all entries with  id[p] to  id[q]

Quick-find defect.
    Union too expensive (N steps).
    Trees are flat, but too expensive to keep them flat.
 */
public class QuickFind implements IQuickUnion {

    private int[] id;

    // O(N)
    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }


    // O(1) -- quick find
    public int root(int p) {
        return id[p];
    }

    // O(N) -- all p values should now be q values
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;

        }
    }

}
