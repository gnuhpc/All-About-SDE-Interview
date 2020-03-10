package org.gnuhpc.interview.leetcode.solutions;

public class ConsecutiveNumbersSum829 {
    //完全是一个数学题，可以从N = base*len + len*(len-1)/2入手，
    // len是这个等差数列的长度，base是等差数列的第一个值
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        for (int len = 1; (len - 1) * len / 2 < N; len++) {
            int base = N - (len - 1) * len / 2;
            if (base % len == 0) {
                count++;
            }
        }
        return count;


    }
}
