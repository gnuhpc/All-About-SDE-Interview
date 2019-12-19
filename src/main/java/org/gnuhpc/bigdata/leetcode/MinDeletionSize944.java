package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 2019/12/15
 */
public class MinDeletionSize944 {
    public int minDeletionSize(String[] A) {
        int len = A[0].length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            count += isIncreasing(A, i);
        }

        return count;
    }

    private int isIncreasing(String[] A, int idx) {
        char start = A[0].charAt(idx);
        for (int i = 1; i < A.length; i++) {
            if (start > A[i].charAt(idx))
                return 1;
            start = A[i].charAt(idx);
        }
        return 0;
    }
}
