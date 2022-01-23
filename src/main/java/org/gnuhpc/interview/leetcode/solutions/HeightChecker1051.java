package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2021/4/28
 */
public class HeightChecker1051 {
    public int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf(heights, heights.length);

        Arrays.sort(copy);

        int res = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != heights[i]) res++;
        }
        return res;
    }
}
