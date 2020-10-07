package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class RunningSum1480 {
    public int[] runningSum(int[] nums) {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) res[i] = nums[i];
            else {
                res[i] = res[i - 1] + nums[i];
            }
        }

        return res;

    }
}
