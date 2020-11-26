package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/22
 */
public class CreateTargetArray1389 {
    public int[] createTargetArray(int[] nums, int[] index) {
        for (int i = 0; i < index.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (index[i] <= index[j]) ++index[j];
            }
        }
        int[] arr = new int[index.length];
        for (int i = 0; i < index.length; ++i) {
            arr[index[i]] = nums[i];
        }
        return arr;
    }
}
