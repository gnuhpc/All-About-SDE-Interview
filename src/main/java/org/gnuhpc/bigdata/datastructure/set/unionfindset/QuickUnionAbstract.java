package org.gnuhpc.bigdata.datastructure.set.unionfindset;

public abstract class QuickUnionAbstract {
    public int[] id;

    abstract void union(int p, int q);
    abstract int find(int p);

    public QuickUnionAbstract(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
}
