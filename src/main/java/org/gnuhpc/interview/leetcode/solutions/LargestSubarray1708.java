package org.gnuhpc.interview.leetcode.solutions;

public class LargestSubarray1708 {
    public int[] largestSubarray(int[] nums, int k) {
        int maxPos = nums.length - k;
        int max = nums[maxPos];

        for (int i = maxPos; i >=0; i--) {
            if(nums[i]>max) {
                maxPos = i;
                max = nums[i];
            }
        }

        int[] res = new int[k];
        for (int i = maxPos; i < maxPos+res.length; i++) {
            res[i-maxPos] = nums[i];
        }
        return res;
    }
}
