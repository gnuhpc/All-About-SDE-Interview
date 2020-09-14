package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/9/14
 */
public class WiggleSort1011 {
    public void wiggleSort(int[] nums) {
        int[] result = nums.clone();
        Arrays.sort(result);
        boolean turn = false;
        int l = 0;
        int r = nums.length - 1;

        for (int i = 0; l <= r; i++) {
            if (turn) {
                nums[i] = result[l];
                l++;
            } else {
                nums[i] = result[r];
                r--;
            }
            turn = !turn;
        }
    }
}
