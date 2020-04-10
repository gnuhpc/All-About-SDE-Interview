package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/10
 */
public class Exchange21 {
    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && (nums[i] % 2) == 1) i++;
            while (i < j && (nums[j] % 2) == 0) j--;
            swap(nums, i, j);
        }
        return nums;
    }


    public void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
