package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/2/21
 */
public class MaximumScore5687 {
    int[] nums;
    int[] multipliers;
    int[][] memos;
    int n;
    int m;

    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        this.n = nums.length;
        this.m = multipliers.length;

        this.memos = new int[m][m];
        for (int i = 0; i < memos.length; i++) {
            Arrays.fill(memos[i], Integer.MIN_VALUE);
        }

        return helper(0, nums.length - 1, 0);
    }

    private int helper(int i, int j, int x) {
        if (x == m) {
            return 0;
        }

        if (memos[i][n - 1 - j] != Integer.MIN_VALUE) {
            return memos[i][n - 1 - j];//为了应对内存超出（n很大的时候）
        }

        int r1 = multipliers[x] * nums[i] + helper(i + 1, j, x + 1);
        int r2 = multipliers[x] * nums[j] + helper(i, j - 1, x + 1);

        int res = Math.max(r1, r2);
        memos[i][n - 1 - j] = res;
        return res;
    }
}
