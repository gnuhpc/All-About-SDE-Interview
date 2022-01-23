package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/11/26
 */
public class CountPrimeSetBits762 {
    public int countPrimeSetBits(int L, int R) {
        //0-20的质数列表，prime[i]为1，则i为质数
        //1000000约等于1024*1024，就是20位

        int[] primes = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1};
        int res = 0;
        for (int i = L; i <= R; i++) {
            int t = Integer.bitCount(i);
            res += primes[t];
        }
        return res;
    }
}