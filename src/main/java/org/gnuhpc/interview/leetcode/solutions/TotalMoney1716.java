package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2022/1/15
 */
public class TotalMoney1716 {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int leftDays = n % 7;

        int sum = 0;
        int base = 28;//1+2+..7

        for (int i = 0; i < weeks; i++) {
            sum += (base + 7 * i);
        }
        base = weeks + 1;
        for (int j = 0; j < leftDays; j++) {
            sum += (base + j);
        }

        return sum;

    }
}
