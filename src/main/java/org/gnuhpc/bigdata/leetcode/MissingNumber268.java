package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class MissingNumber268 {
    public int missingNumber(int[] nums) {
        return sumN(nums.length) - Arrays.stream(nums).sum();
    }

    private int sumN(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum +=i;
        }

        return sum;
    }
}
