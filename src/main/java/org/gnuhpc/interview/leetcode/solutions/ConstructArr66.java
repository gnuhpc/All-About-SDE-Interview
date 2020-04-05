package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/2
 */
public class ConstructArr66 {
    public int[] constructArr(int[] a) {
        if (a.length == 0)
            return new int[0];

        int[] left = new int[a.length];
        int[] right = new int[a.length];
        int[] res = new int[a.length];
        left[0] = 1;
        right[a.length - 1] = 1;

        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        for (int i = 0; i < res.length; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    /*
    1 2 3 4 5

    1 1 2 6 24
    120 60 20 5 1
     */
}
