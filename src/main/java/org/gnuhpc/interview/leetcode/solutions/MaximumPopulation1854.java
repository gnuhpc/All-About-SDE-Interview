package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/23
 */
public class MaximumPopulation1854 {
    int[] hash = new int[3000];

    public int maximumPopulation(int[][] logs) {
        for (int[] i : logs) {
            for (int j = i[0]; j < i[1]; j++) {
                ++hash[j];
            }
        }
        int res = 0;
        for (int i = 0; i < 3000; i++) {
            if (hash[i] > hash[res]) {
                res = i;
            }
        }
        return res;
    }
}
