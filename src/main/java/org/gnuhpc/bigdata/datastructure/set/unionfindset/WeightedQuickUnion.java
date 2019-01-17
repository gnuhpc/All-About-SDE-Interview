package org.gnuhpc.bigdata.datastructure.set.unionfindset;

/*
Weighted quick-union.
    Modify quick-union to avoid tall trees.
    Keep track of size of each component.
    Balance by linking small tree below large one.

Java implementation.
    Almost identical to quick-union.
    Maintain extra array  sz[] to count number of elements
    in the tree rooted at i.

Find.
    Identical to quick-union.
Union.
    Modify quick-union to merge smaller tree into larger tree update the sz[] array.
 */

public class WeightedQuickUnion  implements IQuickUnion {

    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // O(logN)
    //Path compression. Just after computing the root of  i ,
    //set the  id of each examined node to  root(i) :make every other node in path
    //point to its grandparent.
    public int root(int i) {
        while (i != id[i]) {
            // path compression
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // root of p is root of j; p belongs to q's connected component group
    // O(logN)
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid == qid) return;
        if (sz[pid] < sz[qid]) {
            id[pid] = qid; sz[qid] += sz[pid];
        } else {
            id[qid] = pid; sz[pid] += sz[qid];
        }
    }

}
