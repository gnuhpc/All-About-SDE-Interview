package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/25
 */
public class MaxProduct1464 {
    public int maxProduct(int[] nums) {
        int i = 0;
        int j = 0;

        if (nums.length <= 2) {
            return (nums[0] - 1) * (nums[1] - 1);
        }

        i = nums[0];
        for (int k = 1; k < nums.length; k++) {
            if (i <= nums[k]) {
                j = i;
                i = nums[k];
            } else if (j <= nums[k]) {
                j = nums[k];
            }
        }
        return (i - 1) * (j - 1);
    }
}
