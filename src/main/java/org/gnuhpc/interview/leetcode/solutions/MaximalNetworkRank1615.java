package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class MaximalNetworkRank1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] map = new boolean[n][n];
        int[] deg = new int[n];
        for (int[] i : roads) {
            deg[i[0]]++;
            deg[i[1]]++;
            map[i[0]][i[1]] = map[i[1]][i[0]] = true;
        }
        int maxn = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                maxn = Math.max(maxn, map[i][j] ? deg[i] + deg[j] - 1 : deg[i] + deg[j]);
        return maxn;
    }
}
