package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/28
 */
public class ThirdMax414 {
    public int thirdMax(int[] nums) {

        long max = Long.MIN_VALUE, secondMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = nums[i];
            } else if (nums[i] > secondMax && nums[i] != max) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] > thirdMax && nums[i] != secondMax && nums[i] != max) {
                thirdMax = nums[i];
            }
        }
        if (thirdMax == Long.MIN_VALUE) {
            return (int) max;
        }
        return (int) thirdMax;
    }
}
