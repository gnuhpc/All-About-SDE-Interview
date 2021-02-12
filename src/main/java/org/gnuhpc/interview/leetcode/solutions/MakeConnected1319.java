package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

/**
 * Copyright gnuhpc 2021/1/23
 */
public class MakeConnected1319 {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        UnionFind uf = new UnionFind(n);

        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }

        return uf.count() - 1;
    }
}
