package org.gnuhpc.interview.leetcode.solutions;

// 位操作
// https://leetcode.com/problems/sum-of-two-integers/discuss/84278/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
public class HammingWeight191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    public static int hammingWeight2(int n) {
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }

    public static int hammingWeight3(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) count++;
        }
        return count;
    }
}
