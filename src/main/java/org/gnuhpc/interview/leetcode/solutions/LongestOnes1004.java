package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class LongestOnes1004 {
    public int longestOnes(int[] A, int K) {
        int start = 0, end = 0, zeroCount = 0, res = 0;

        for (; end < A.length; end++) {
            if (A[end] == 0) zeroCount++;
            while (zeroCount > K) {
                if (A[start++] == 0) zeroCount--;
            }

            res = Math.max(res, end - start + 1);
        }

        return res;
    }


    @Test
    public void test() {
        longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
    }
}
