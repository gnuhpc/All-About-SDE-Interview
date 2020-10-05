package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class CanPlaceFlowers605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int canInsertCounts = 0;

        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                i++;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                i++;
                canInsertCounts++;
            }
        }

        return canInsertCounts >= n;

    }
}
