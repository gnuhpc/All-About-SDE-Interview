package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/6/17
 */
public class MaxScoreSightseeingPair1014 {
    public int maxScoreSightseeingPair(int[] A) {
        if (A == null || A.length < 2) return 0;
        int pre = A[0] + 0;
        int res = Integer.MIN_VALUE;

        for (int j = 1; j < A.length; j++) {
            res = Math.max(res, A[j] - j + pre);
            pre = Math.max(pre, A[j] + j);
        }
        return res;
    }
}
