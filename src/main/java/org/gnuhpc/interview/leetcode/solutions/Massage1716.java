package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/8/2
 */
public class Massage1716 {
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int massage(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        return max(nums, 0);
    }

    private int max(int[] nums, int pos) {
        int res = 0;
        if (pos >= nums.length) return 0;
        if (memo.get(pos) != null) return memo.get(pos);
        res = Math.max(nums[pos] + max(nums, pos + 2), max(nums, pos + 1));
        memo.put(pos, res);
        return res;
    }
}
