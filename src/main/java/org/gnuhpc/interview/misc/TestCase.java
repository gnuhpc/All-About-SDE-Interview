package org.gnuhpc.interview.misc;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/4/29
 */
public class TestCase {
    @Test
    public void test() {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length <= 2) {
            for (int i = 0; i < nums.length; i++) {
                if (target == nums[i]) return i;

            }
            return -1;
        }

        int idx = findIdx(nums);

        System.out.println(idx);
        int res = bsearch(nums, target, 0, idx);
        if (res == -1) return bsearch(nums, target, idx, nums.length - 1);
        else return res;
    }

    private int findIdx(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            int mid_val = nums[mid], left_val = nums[mid - 1], right_val = nums[mid + 1];
            if (mid_val > left_val && mid_val > right_val) return mid;
            if (mid_val > left_val && mid_val < right_val) {
                if (mid_val > nums[0]) {
                    low = mid;
                } else {
                    high = mid;
                }
            } else high = mid;
        }

        if (nums[low] > nums[high]) return low;

        int mid_val = nums[low],
                left_val = nums[low - 1],
                right_val = nums[low + 1];
        if (mid_val > left_val && mid_val > right_val) return low;
        else return high;
    }

    public int bsearch(int[] nums, int target, int start, int end) {
        if (start < 0 || end > nums.length) {
            return -1;
        }
        //相邻就退出
        while (start + 1 < end) {
            //不会越界
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                end = mid; //往前找
                //If you'd like to fetch the last idx of the target, use the following statement instead:
//                start = mid; //往后找
            } else if (target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //范围缩小，double check
        // 最后范围内就剩下start和end两个
        if (target == nums[start]) {
            return start;
        }
        if (target == nums[end]) {
            return end;
        }
        return -1;
    }
}
