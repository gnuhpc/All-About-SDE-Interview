package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/18
 */
public class MinKBitFlips995 {
    public int minKBitFlips(int[] A, int K) {
        int len = A.length;
        int num = len - K + 1;
        int res = 0;
        for (int i = 0; i < num; i++) {
            if (A[i] == 0) {
                res++;
                int j = i;
                int wsize = K;
                while (wsize-- != 0) {
                    A[j] ^= 1;
                    j++;
                }
            }
        }
        for (int i = num; i < len; i++) {
            if (A[i] == 0) {
                return -1;
            }
        }
        return res;
    }
}
