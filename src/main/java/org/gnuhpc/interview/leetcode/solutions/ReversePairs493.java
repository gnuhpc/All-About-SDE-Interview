package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/28
 */
public class ReversePairs493 {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int left, int right) {
        if (right <= left) {
            return 0;
        }
        int mid = (left + right) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        // 中间数组用于合并
        int[] cache = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, tmp = left;
        while (j <= right) {
            while (tmp <= mid && nums[tmp] <= 2 * (long) nums[j]) tmp++;
            while (i <= mid && nums[i] < nums[j]) cache[k++] = nums[i++];
            cache[k++] = nums[j++];
            count += mid - tmp + 1;
        }
        while (i <= mid) cache[k++] = nums[i++];
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }
}
