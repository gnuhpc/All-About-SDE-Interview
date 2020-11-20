package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/14
 */
public class MissingNumber1228 {
    public int missingNumber(int[] arr) {
        int diff1 = arr[1] - arr[0];
        int diff2 = arr[2] - arr[1];
        if (diff1 == 0 && diff2 == 0) return arr[0];
        int diff = Math.abs(diff1) > Math.abs(diff2) ? diff2 : diff1;

        int pre = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] != diff) {
                pre = arr[i - 1];
            }
        }
        return pre + diff;
    }
}
