package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;

public class FindRedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        QuickUnion unionFind = new QuickUnion(edges.length);

        // 第一条边肯定未记录至树中，可直接合并节点
        unionFind.union(edges[0][0] - 1, edges[0][1] - 1);
        for (int i = 1; i < edges.length; i++) {
            if (unionFind.isConnected(edges[i][0] - 1,edges[i][1] - 1)) {
                res[0] = edges[i][0];
                res[1] = edges[i][1];
            } else {
                unionFind.union(edges[i][0] - 1, edges[i][1] - 1);
            }
        }
        return res;

    }
}
