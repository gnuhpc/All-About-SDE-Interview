package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/10
 */
public class MaxCount598 {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
