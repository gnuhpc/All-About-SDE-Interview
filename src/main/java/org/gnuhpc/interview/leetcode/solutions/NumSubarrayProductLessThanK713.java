package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/3
 */
public class NumSubarrayProductLessThanK713 {
    /*
    双指针
    链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/javachao-xiang-xi-jie-xi-kan-bu-dong-lai-da-wo-by-/
    */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int now = 1, times = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            now *= nums[right];
            while (now >= k) now /= nums[left++];
            times += right - left + 1;
        }
        return times;
    }

}
