package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

/*
https://www.jianshu.com/p/add1398e552a
 */
public class MaxCoins312 {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums.length][nums.length];

        return helper(nums, dp, 0, nums.length - 1);
    }

    private int helper(int[] nums, int[][] dp, int l, int r) {
        if (l > r) {
            return 0;
        }

        if (dp[l][r] != 0) {
            return dp[l][r];
        }

        int max = nums[l];
        for (int i = l; i <= r; ++i) {
            int cur = helper(nums, dp, l, i - 1)
                    + get(nums, l - 1) * nums[i] * get(nums, r + 1)
                    + helper(nums, dp, i + 1, r);

            max = Math.max(max, cur);
        }

        dp[l][r] = max;

        return max;
    }

    private int get(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return 1;
        }

        return nums[i];
    }
}
