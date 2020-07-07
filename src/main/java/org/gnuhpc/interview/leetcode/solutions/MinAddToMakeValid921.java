package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/5
 */
public class MinAddToMakeValid921 {
    public int minAddToMakeValid(String S) {
        int toMatch = 0;

        char[] arr = S.toCharArray();
        int i = 0;

        int res = 0;
        for (; i < arr.length; i++) {
            char c = arr[i];
            if (c == '(') toMatch++;
            if (c == ')') toMatch--;

            if (toMatch < 0) {
                res += -(toMatch);
                toMatch = 0;
            }
        }

        if (i == arr.length && toMatch > 0) res += toMatch;

        return res;
    }
}
