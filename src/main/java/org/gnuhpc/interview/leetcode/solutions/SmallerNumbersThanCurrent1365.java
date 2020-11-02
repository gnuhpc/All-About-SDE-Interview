package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/26
 */
public class SmallerNumbersThanCurrent1365 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] map = new int[101];
        int total = nums.length;
        int[] res = new int[total];

        for (int n : nums) {
            map[n]++;
        }
        int j = 0;
        for (int n : nums) {
            int tmp = 0;
            for (int i = n - 1; i >= 0; i--) {
                tmp += map[i];
            }

            res[j++] = tmp;
        }

        return res;
    }
}
