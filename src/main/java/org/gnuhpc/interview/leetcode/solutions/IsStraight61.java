package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/2/17
 */
public class IsStraight61 {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroCnt = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 || nums[i - 1] == 0) {
                zeroCnt++;
            } else if (nums[i] == nums[i - 1]) return false;
            else {
                zeroCnt -= (nums[i] - nums[i - 1] - 1);
            }
            if (zeroCnt < 0) return false;
        }
        return true;
    }
}
