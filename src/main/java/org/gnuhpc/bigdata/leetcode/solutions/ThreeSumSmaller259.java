package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class ThreeSumSmaller259 {
    public int threeSumSmaller(int[] nums, int target) {
        int[] res = new int[1];
        if (nums == null || nums.length < 3) return 0;
        if (nums.length == 3) {
            int sum = 0;
            for (int n : nums) {
                sum += n;
            }

            if (sum < target) return 1;
            else return 0;
        }

        int prev = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            twoSumLessThan(nums, target - nums[i], i + 1, res);
        }

        return res[0];
    }

    private void twoSumLessThan(int[] nums, int target, int startIdx, int[] res) {
        int left = startIdx;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                res[0] += right - left;
                left++;
            }
            else {
                right--;
            }
        }
    }

}
