package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/24
 */
public class FlipAndInvertImage832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int a = A[i][l], b = A[i][r];
                A[i][l] = b;
                A[i][r] = a;
            }
            for (int j = 0; j < n; j++) A[i][j] = 1 - A[i][j];
        }
        return A;
    }
}
