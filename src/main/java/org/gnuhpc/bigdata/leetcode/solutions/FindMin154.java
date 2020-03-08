package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-23 9:45
 * Version: 0.1
 */
public class FindMin154 {
    /**
     * 普通迭代二分
     * time : O(logn)  worst: O(n);
     * space : O(1);
     */
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        //only need to add the following while loop on top of the solution
        //for Part I
        //if two line segments have overlap, remove the overlap.
        //so, the problem can be solved as Part I
        while (nums[end] == nums[start] && end > start) {
            end--;
        }

        while (start + 1 < end) {
            int target = nums[end];
            int mid = (end - start) / 2 + start;
            if (nums[mid] <= target) {
                end = mid;
            }
            else if (nums[mid] > target) {
                start = mid;
            }
        }

        return Math.min(nums[start], nums[end]);

    }

}
