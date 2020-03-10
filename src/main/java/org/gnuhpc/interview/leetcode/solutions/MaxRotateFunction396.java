package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 19-8-22
 */
public class MaxRotateFunction396 {
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        int sum = 0;
        int candidate = 0;

        for (int i = 0; i < n; i++) {
            sum += A[i];
            candidate += A[i] * i;
        }
        int best = candidate;

        for (int i = n - 1; i > 0; i--) {
            candidate = candidate + sum - A[i] * n;
            best = Math.max(best, candidate);
        }
        return best;
    }
}
