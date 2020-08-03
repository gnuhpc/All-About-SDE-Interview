package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class CheckPerfectNumber507 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int sum = 1;
        int div = 2;
        int limit = num / 2;
        while (div < limit) {
            if (num % div == 0) {
                limit = num / div;
                sum += div;
                sum += (num / div);
            }
            div++;
        }
        return num == sum;
    }
}
