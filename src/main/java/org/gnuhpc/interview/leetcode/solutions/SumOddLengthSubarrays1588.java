package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/19
 */
public class SumOddLengthSubarrays1588 {
    public int sumOddLengthSubarrays(int[] arr) {
        if (arr.length == 1) return arr[0];
        if (arr.length == 2) return arr[0] + arr[1];

        int[] presum = new int[arr.length + 1];
        int res = 0;
        for (int i = 1; i <= arr.length; i++) {
            presum[i] = presum[i - 1] + arr[i - 1];
        }

        int i = 0, j = 1, step = 2, initj = 1;
        while (j <= arr.length) {
            while (j <= arr.length) {
                res += presum[j] - presum[i];
                i++;
                j++;
            }

            i = 0;
            j = (initj + step);
            initj = j;
        }


        return res;
    }
}
