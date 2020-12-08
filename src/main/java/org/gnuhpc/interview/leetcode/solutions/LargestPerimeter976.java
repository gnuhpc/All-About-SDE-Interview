package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/11/29
 */
public class LargestPerimeter976 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int res = 0;
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i] < A[i - 1] + A[i - 2]) {
                res = A[i] + A[i - 1] + A[i - 2];
                return res;
            }
        }

        return 0;
    }
}
