package org.gnuhpc.bigdata.leetcode.solutions;

public class FindMaxConsecutiveOnes485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) count++;
            else {
                res = Math.max(res, count);
                count = 0;
            }
        }

        res = Math.max(res, count);

        return res;

    }
}
