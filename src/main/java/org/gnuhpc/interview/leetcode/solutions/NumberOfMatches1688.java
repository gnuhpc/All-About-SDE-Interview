package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/4
 */
public class NumberOfMatches1688 {
    public int numberOfMatches(int n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
                res += n;
            } else {
                n = (n - 1) / 2 + 1;
                res += (n - 1);
            }
        }

        return res;

    }
}
