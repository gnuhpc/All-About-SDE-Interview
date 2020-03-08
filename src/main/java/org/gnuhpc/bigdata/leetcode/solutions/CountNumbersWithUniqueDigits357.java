package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class CountNumbersWithUniqueDigits357 {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 1) {
            return 10;
        }
        else if (n > 10) {
            return 0;
        }
        else if (n == 0) {
            return 1;
        }
        int res = 9;
        for (int i = 9, count = 1; count <= n - 1; i--, count++) {
            res *= i;
        }
        return res + countNumbersWithUniqueDigits(n - 1);
    }

    @Test
    public void test() {
        System.out.println(countNumbersWithUniqueDigits(3));
    }
}
