package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright gnuhpc 2021/4/4
 */
public class MinSubsequence1403 {
    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        Arrays.sort(nums);
        int ans = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            list.add(nums[i]);
            sum -= nums[i];
            ans += nums[i];
            if (ans > sum) break;
        }
        return list;
    }
}
