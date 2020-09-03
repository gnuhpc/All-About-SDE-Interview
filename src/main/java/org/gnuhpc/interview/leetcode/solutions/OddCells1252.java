package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/1
 */
public class OddCells1252 {
    public int oddCells(int n, int m, int[][] indices) {
        int[][] arr = new int[n][m];
        for (int[] co : indices) {
            int row = co[0];
            int col = co[1];

            for (int i = 0; i < m; i++) {
                arr[row][i]++;
            }

            for (int i = 0; i < n; i++) {
                arr[i][col]++;
            }
        }

        int res = 0;
        for (int[] row : arr) {
            for (int num : row) {
                if (num % 2 == 1) res++;
            }
        }

        return res;

    }
}
