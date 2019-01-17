package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-23 9:45
 * Version: 0.1
 */
public class FindMin154 {
    /*
    Method1 ： 递归
    This is a follow-up problem of finding minimum element in rotated sorted array without duplicate elements.
    We only need to add one more condition,
    which checks if the left-most element and the right-most element are equal.
    If they are we can simply drop one of them.
    In my solution below,
    I drop the left element whenever the left-most equals to the right-most.
     */
    public int findMin(int[] num) {
        return findMin(num, 0, num.length-1);
    }

    public int findMin(int[] num, int left, int right) {
        if (right == left) {
            return num[left];
        }
        if (right == left + 1) {
            return Math.min(num[left], num[right]);
        }
        // 3 3 1 3 3 3

        int middle = (right - left) / 2 + left;
        // already sorted
        if (num[right] > num[left]) {
            return num[left];
            //right shift one
        } else if (num[right] == num[left]) {
            return findMin(num, left + 1, right);
            //go right
        } else if (num[middle] >= num[left]) {
            return findMin(num, middle, right);
            //go left
        } else {
            return findMin(num, left, middle);
        }
    }

    /**
     * 普通迭代二分
     * time : O(logn)  worst: O(n);
     * space : O(1);
     */
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int target = nums[end];
            int mid = (end - start) / 2 + start;
            if (nums[mid] < target) {
                end = mid;
            } else if (nums[mid] > target) {
                start = mid;
            } else {
                end--;
            }
        }

        return Math.min(nums[start],nums[end]);

    }

}
