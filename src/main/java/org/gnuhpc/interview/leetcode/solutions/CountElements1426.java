package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/25
 */
public class CountElements1426 {
    public int countElements(int[] arr) {
        int[] map = new int[1001];
        for (int n : arr) {
            map[n]++;
        }
        int res = 0;
        for (int n : arr) {
            if (n < 1000) {
                if (map[n + 1] > 0) res++;
            }
        }
        return res;
    }
}
