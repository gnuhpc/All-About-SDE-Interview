package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/11/1
 */
public class NumWaterBottles1518 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int sum = 0;
        for (int i = numBottles; i > 0; i--) {
            count++;
            if (count == numExchange) {
                i++;
                count = 0;
            }
            sum++;
        }
        return sum;
    }
}
