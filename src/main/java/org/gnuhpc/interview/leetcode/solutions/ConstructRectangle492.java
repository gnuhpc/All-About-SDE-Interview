package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/18
 */
public class ConstructRectangle492 {
    public int[] constructRectangle(int area) {
        for (int i = (int) Math.sqrt(area); i > 0; i--) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
        return new int[]{};
    }
}
