package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/17
 */
public class CheckStraightLine1323 {
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        // 比较和第一个点之间的斜率即可
        int dy = coordinates[1][1] - coordinates[0][1];
        int dx = coordinates[1][0] - coordinates[0][0];
        for (int i = 1; i < n; i++) {
            int dyi = coordinates[i][1] - coordinates[0][1];
            int dxi = coordinates[i][0] - coordinates[0][0];
            // dy/dx = dyi/dxi
            if (dy * dxi != dyi * dx) return false;
        }
        return true;
    }
}
