package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/4/3
 */
public class FindSpecialInteger1287 {
    public int findSpecialInteger(int[] arr) {
        int len = arr.length;
        if (len == 1) return arr[0];

        int idx1 = len / 4 + 1;
        int idx2 = len / 2;
        int idx3 = len * 3 / 4 - 1;

        if (arr[idx1] == arr[0]) return arr[0];
        if (arr[idx1] == arr[idx1 - 1] && arr[idx1] == arr[idx1 + 1]) return arr[idx1];
        if (arr[idx2] == arr[idx1]) return arr[idx1];
        if (arr[idx2] == arr[idx2 - 1] && arr[idx2] == arr[idx2 + 1]) return arr[idx2];
        if (arr[idx2] == arr[idx3]) return arr[idx2];
        if (arr[idx3] == arr[len - 1]) return arr[idx3];

        return 0;
    }
}
