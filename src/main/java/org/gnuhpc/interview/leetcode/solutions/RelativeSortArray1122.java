package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/14
 */
public class RelativeSortArray1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int n : arr1) {
            map[n]++;
        }

        int[] res = new int[arr1.length];
        int idx = 0;

        for (int n : arr2) {
            int nums = map[n];
            for (int i = 0; i < nums; i++) {
                res[idx++] = n;
                map[n] -= 1;
            }
        }

        if (idx != res.length) {
            for (int i = 0; i < map.length; i++) {
                if (map[i] != 0) {
                    for (int j = 0; j < map[i]; j++)
                        res[idx++] = i;
                }
            }
        }

        return res;
    }
}
