package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/1/16
 */
public class FindDuplicates442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ret = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[(Math.abs(nums[i]) - 1) % n] = -Math.abs(nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) ret.add(i + 1);
        }
        return ret;
    }
}
