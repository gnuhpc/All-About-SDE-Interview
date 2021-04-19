package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/28
 */
public class SubtractProductAndSum1281 {
    public int subtractProductAndSum(int n) {
        if (n < 9) return 0;
        char[] arr = String.valueOf(n).toCharArray();
        int sum = 0;
        int mul = 1;

        for (char c : arr) {
            int num = c - '0';
            sum += num;
            mul *= num;
        }

        return mul - sum;
    }
}
