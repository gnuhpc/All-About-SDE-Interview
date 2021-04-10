package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/17
 */
public class Shuffle1470 {
    public int[] shuffle(int[] nums, int n) {
        int index = 0;
        int[] result = new int[nums.length];
        for (int i = 0; i < n; i++) {
            result[index++] = nums[i];
            result[index++] = nums[n + i];
        }
        return result;
    }
}
