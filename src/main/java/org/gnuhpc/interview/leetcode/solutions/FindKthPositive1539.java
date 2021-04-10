package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/12
 */
public class FindKthPositive1539 {
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] - mid >= k + 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return k + left;

    }
}
