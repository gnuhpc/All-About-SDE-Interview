package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2022/1/1
 */
public class Construct2DArray2022 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int[][] res = new int[m][n];
        if (m * n != original.length) return new int[0][0];
        int x = 0;
        for (int i = 0; i < original.length; ) {
            for (int y = 0; y < n; y++) {
                res[x][y] = original[i];
                i++;
            }
            x++;
        }

        return res;
    }
}
