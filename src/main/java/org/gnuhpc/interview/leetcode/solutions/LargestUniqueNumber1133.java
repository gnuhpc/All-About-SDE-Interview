package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/29
 */
public class LargestUniqueNumber1133 {
    public int largestUniqueNumber(int[] A) {
        int[] map = new int[1001];
        int max = 0;
        for (int n : A) {
            map[n]++;
            max = Math.max(max, n);
        }
        int i = max;
        for (; i >= 0; i--) {
            if (map[i] == 1) break;
        }

        return i;
    }
}
