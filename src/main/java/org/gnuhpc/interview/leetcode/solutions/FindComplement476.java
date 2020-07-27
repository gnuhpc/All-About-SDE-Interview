package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/12
 */
public class FindComplement476 {
    public int findComplement(int num) {
        if (num == 0) return 0;
        int res = 0;
        int k = 0;

        while (num != 0) {
            //System.out.println((num & 1) ^1);
            res += ((num & 1) ^ 1) * Math.pow(2, k);
            num >>= 1;
            k++;
        }

        return res;
    }
}
