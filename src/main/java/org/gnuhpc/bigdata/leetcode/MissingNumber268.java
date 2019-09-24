package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class MissingNumber268 {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length + 1;
        int sum = (n * (n - 1)) / 2;
        for(int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
