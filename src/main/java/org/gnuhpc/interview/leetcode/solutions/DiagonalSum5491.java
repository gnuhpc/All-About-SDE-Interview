package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/6
 */
public class DiagonalSum5491 {
    public int diagonalSum(int[][] mat) {
        if (mat.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            sum += mat[i][i];
        }
        for (int i = mat.length - 1; i >= 0; i--) {
            sum += mat[i][mat.length - 1 - i];
        }
        int length = mat.length;
        if (length % 2 == 1) {
            length /= 2;
            sum -= mat[length][length];
        }
        return sum;
    }
}
