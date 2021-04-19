package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/12
 */
public class DecompressRLElist1313 {
    public int[] decompressRLElist(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }

        int[] res = new int[len];

        for (int i = 1, j = 0; j < res.length; i += 2) {
            int freq = nums[i - 1];
            int num = nums[i];
            while (freq > 0) {
                res[j++] = num;
                freq--;
            }
        }

        return res;
    }
}
