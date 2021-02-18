package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/6
 */
public class MaxScore1423 {
    public int maxScore(int[] nums, int k) {
        int n = nums.length, len = n - k;
        int sum = 0, cur = 0;
        for (int i = 0; i < n; i++) sum += nums[i];
        for (int i = 0; i < len; i++) cur += nums[i];
        int min = cur;
        for (int i = len; i < n; i++) {
            cur = cur + nums[i] - nums[i - len];
            min = Math.min(min, cur);
        }
        return sum - min;
    }
}
