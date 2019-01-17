package org.gnuhpc.bigdata.datastructure.set.unionfindset;

public interface IQuickUnion {

    void union(int p, int q);
    int root(int p);
    default boolean connected(int p, int q){
        return root(p) == root(q);
    };
}
