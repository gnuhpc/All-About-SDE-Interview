package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/4/10
 */
public class FindClosestElements658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left, right, mid;
        List<Integer> ans = new ArrayList<Integer>();

        // 1. binary search to locate x
        left = 0;
        right = arr.length - 1;
        mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                right = mid;
            } else if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // now mid[left] is either smaller than x or equal to x

        // 2. determine the scope of k nearest numbers
        right = left + 1;
        for (int i = k; i > 0; i--) {
            // left & right works as double-pointer to determine the ans scope
            if (0 <= left && (right >= arr.length || x - arr[left] <= arr[right] - x)) {
                // move left if right is end or arr[left] is nearer than arr[right]
                left--;
            } else {
                right++;
            }
        }
        // now arr(left, right) (aka. arr[left+1, right-1]) are the ans

        // 3. return answers in List structure
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
