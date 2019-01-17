package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-23 9:45
 * Version: 0.1
 */
public class FindMin153 {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int start = 0, end = nums.length-1;

        // find the first element <= target
        while (start + 1 < end) {
            //注意这个锚定点: First position <= Last Number
            int target = nums[nums.length - 1];
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return Math.min(nums[start],nums[end]);

    }
}
