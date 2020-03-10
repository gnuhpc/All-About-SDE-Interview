package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class SearchInsert35 {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;

        if (target < nums[0]) {
            return 0;
        }

        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        // find the last number less than target
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] < target) {
            return end + 1;
        }

        return start + 1;
    }

    @Test
    public void test() {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
    }
}
