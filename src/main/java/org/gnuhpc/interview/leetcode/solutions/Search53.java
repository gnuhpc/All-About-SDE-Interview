package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/18
 */
public class Search53 {
    public int search(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums.length == 0) return 0;

        //二分法向前找
        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            if (nums[mid] == target) end = mid;
            else if (nums[mid] > target) end = mid;
            else start = mid;
        }

        //尽可能取前边的
        if (nums[start] == target) res[0] = start;
        else if (nums[end] == target) res[0] = end;

        start = 0;
        end = nums.length - 1;

        //二分法向后找
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            if (nums[mid] == target) start = mid;
            else if (nums[mid] > target) end = mid;
            else start = mid;
        }

        //尽可能取后边的
        if (nums[end] == target) res[1] = end;
        else if (nums[start] == target) res[1] = start;
        if (res[1] == res[0] && res[1] == -1) return 0;
        return res[1] - res[0] + 1;
    }
}
