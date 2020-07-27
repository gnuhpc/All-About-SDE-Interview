package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/12
 */
public class BitwiseComplement1009 {
    public int bitwiseComplement(int N) {

        if (N == 0) return 1;
        int res = 0;
        int k = 0;

        while (N != 0) {
            //System.out.println((num & 1) ^1);
            res += ((N & 1) ^ 1) * Math.pow(2, k);
            N >>= 1;
            k++;
        }

        return res;
    }
}
