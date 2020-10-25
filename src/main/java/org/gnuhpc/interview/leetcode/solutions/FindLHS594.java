package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/10/14
 */
public class FindLHS594 {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0, end = 0, res = 0;
        for (; end < nums.length; end++) {
            if (nums[end] - nums[begin] == 1)
                res = Math.max(res, end - begin + 1);
            while (nums[end] - nums[begin] > 1)
                begin++;
        }
        return res;
    }
}
