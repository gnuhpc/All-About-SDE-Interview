package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/11
 */
public class MinMoves453 {
    public int minMoves(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int out = 0;
        //遍历一遍数组找到z数组中的最小值minValue
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            }
        }
        //再遍历一遍数组,计算各个值与最小值之差的总和
        for (int i = 0; i < nums.length; i++) {
            out += (nums[i] - minValue);
        }
        return out;
    }
}
