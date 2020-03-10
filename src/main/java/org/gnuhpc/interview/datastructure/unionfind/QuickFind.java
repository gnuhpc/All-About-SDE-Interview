package org.gnuhpc.interview.datastructure.unionfind;

// Eager solution 使用比较少
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
public class QuickFind extends QuickUnionAbstract {

    public QuickFind(int N) {
        super(N);
    }

    // O(1) -- quick find
    public int find(int p) {
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
