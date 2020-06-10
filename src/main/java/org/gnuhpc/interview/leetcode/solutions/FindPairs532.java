package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/6/1
 */
public class FindPairs532 {
    public int findPairs(int[] nums, int k) {
        //k 分情况讨论
        int res = 0;
        if (k < 0) return 0;
        if (k == 0) {
            Set<Integer> setToUse = new HashSet<>();
            Set<Integer> setUsed = new HashSet<>();
            for (int n : nums) {
                if (setToUse.contains(n) && !setUsed.contains(n)) {
                    res++;
                    setUsed.add(n);
                } else {
                    setToUse.add(n);
                }
            }

            return res;
        }

        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                if (set.contains(nums[i] + k)) count++;
                if (set.contains(nums[i] - k)) count++;
            }
        }

        return count;
    }
}
