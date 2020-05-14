package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/11
 */
public class SuperPow372 {
    /*
   2^23 = (2^2)^10 * 2^3, 所以我们可以从b的最高位开始，算出个结果存入res，然后到下一位是，res的十次方再乘以a的该位次方再对1337取余
   */
    public int superPow(int a, int[] b) {
        int res = 1;
        for (int i = 0; i < b.length; ++i) {
            res = pow(res, 10) * pow(a, b[i]) % 1337;
        }
        return res;
    }

    int pow(int x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x % 1337;
        return pow(x % 1337, n / 2) * pow(x % 1337, n - n / 2) % 1337;
    }


}
