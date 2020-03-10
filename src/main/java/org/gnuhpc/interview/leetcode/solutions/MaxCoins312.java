package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class MaxCoins312 {
    public int maxCoins(int[] iNums) {
        int len = iNums.length;
        int[] nums = new int[len + 2];
        for (int i = 0; i < len + 2; i++) {
            if (i == 0 || i == len + 1)
                nums[i] = 1;
            else
                nums[i] = iNums[i - 1];
        }

        int[][] memo = new int[len + 2][len + 2];
        return burst(memo, nums, 0, len + 1);
    }

    /*
    分治的思路中：如果选择考虑先戳爆第i个气球，左半部分和右半部分的操作(如左边最后一个的右边是i)都需要依赖第i个气球
    因为分治是将左半部分看做一个独立的子问题，不依赖于i，因此不能考虑先戳
    那么考虑第i个气球是最后一个被戳破的气球.就不会被左右半边影响.
    和走楼梯一样，都是从最后的一步来推
     */

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }
}
