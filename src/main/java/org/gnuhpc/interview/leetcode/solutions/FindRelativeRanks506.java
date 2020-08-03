package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class FindRelativeRanks506 {
    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        Arrays.sort(nums);
        int order = 1;
        String[] ans = new String[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int key = nums[i];
            switch (order) {
                case 1:
                    ans[map.get(key)] = "Gold Medal";
                    break;
                case 2:
                    ans[map.get(key)] = "Silver Medal";
                    break;
                case 3:
                    ans[map.get(key)] = "Bronze Medal";
                    break;
                default:
                    ans[map.get(key)] = String.valueOf(order);
            }

            order++;
        }

        return ans;
    }
}
