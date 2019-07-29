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

public class WeightedQuickUnion extends QuickUnion{

    //用来计数的，自己是一个区域的就是1，这个区域有几个元素
    public int[] sz;

    public WeightedQuickUnion(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    // find of p is find of j; p belongs to q's connected component group
    // O(logN)

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return;
        //• merge smaller tree into larger tree
        //• update the sz[] array.
        if (sz[pid] < sz[qid]) {
            id[pid] = qid; sz[qid] += sz[pid];
        } else {
            id[qid] = pid; sz[pid] += sz[qid];
        }
    }

}
