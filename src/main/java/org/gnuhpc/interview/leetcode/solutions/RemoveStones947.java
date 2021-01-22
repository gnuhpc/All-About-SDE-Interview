package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/1/15
 */
public class RemoveStones947 {

    public int removeStones(int[][] stones) {
        int total = stones.length;
        UnionFind unionFind = new UnionFind(total);

        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                int[] stone1 = stones[i];
                int[] stone2 = stones[j];

                if (stone1[0] == stone2[0] || stone1[1] == stone2[1]) {
                    if (!unionFind.isConnected(i, j)) {
                        unionFind.union(i, j);
                        total--;
                    }
                }
            }
        }

        return total;
    }
}
