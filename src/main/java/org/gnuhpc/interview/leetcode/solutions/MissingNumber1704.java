package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/17
 */
public class MissingNumber1704 {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        int sum = n * (n + 1) / 2;

        for (int i : nums) {
            sum -= i;
        }
        return sum;
    }
}
