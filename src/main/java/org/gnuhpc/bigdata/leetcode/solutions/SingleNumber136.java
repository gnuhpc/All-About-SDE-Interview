package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber136 {
    public int singleNumber(int[] nums) {
        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (!res.remove(nums[i])) {
                res.add(nums[i]);
            }
        }

        return res.iterator().next();
    }
}
