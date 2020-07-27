package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/11
 */
public class FindRadius475 {
    public int findRadius(int[] houses, int[] heaters) {
        int max = 0;
        for (int i = 0; i < houses.length; i++) {
            int close = Integer.MAX_VALUE;
            for (int j = 0; j < heaters.length; j++) {
                if (Math.abs(houses[i] - heaters[j]) < close) {
                    close = Math.abs(houses[i] - heaters[j]);
                }
            }
            if (close > max) {
                max = close;
            }
        }
        return max;
    }
}
