package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.UnionFind;

public class FindRedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        UnionFind uf = new UnionFind(edges.length+1);

        for (int i = 0; i < edges.length; i++) {
            if (uf.isConnected(edges[i][0] , edges[i][1] )) {
                res[0] = edges[i][0];
                res[1] = edges[i][1];
            } else {
                uf.union(edges[i][0], edges[i][1]);
            }
        }
        return res;
    }
}
