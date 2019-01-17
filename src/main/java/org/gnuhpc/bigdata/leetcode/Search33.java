package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-21 22:27
 * Version: 0.1
 */
public class Search33 {
    /*
    Method : 一次二分法
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            //总分两种情况，两条线
            if (nums[start] < nums[mid]) {
                //分两种情况
                //1. target在 nums[start] 和 nums[mid] 之间，即符合这个上升趋势
                // 例如 2,3,4,5,6,7,1,target=4, nums[start] = 2, nums[mid] = 5
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid; //查找左半边

                    //2. target不在 nums[start] 和 nums[mid] 之间
                    // 例如 2,3,4,5,6,7,1,target=7, nums[start] = 2, nums[mid] = 5
                } else {
                    start = mid; //查找右半边
                }
            } else {
                // 例如 6,7,1,2,3,4,5  nums[end] = 6, nums[mid] = 2
                //分两种情况
                //1. target在 nums[mid] 和 nums[end] 之间，即符合这个上升趋势
                // target = 3
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid; //查找右半边
                } else {
                    end = mid; //查找左半边
                }
            }
        } // while

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    @Test
    public void test(){
        System.out.println(search(new int[]{4,5,6,7,0,1,2},3));
    }
}
