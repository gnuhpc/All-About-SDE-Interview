package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Copyright gnuhpc 19-8-21
 */
public class IsPerfectSquare367 {
    public boolean isPerfectSquare(int num) {
        if (num <= 4) {
            if (num == 1 || num == 4) {
                return true;
            }
            else {
                return false;
            }
        }

        long left = 1, right = num / 2;
        while (left + 1 < right) {
            long mid = (right - left) / 2 + left;

            long temp = mid * mid;

            if (temp == num) return true;
            else if (temp > num) right = mid;
            else left = mid;
        }

        if (left * left == num) return true;
        if (right * right == num) return true;

        return false;
    }

    @Test
    public void test() {
//        System.out.println(isPerfectSquare(16));
//        System.out.println(isPerfectSquare(20));
//        System.out.println(isPerfectSquare(256));
        System.out.println(isPerfectSquare(808201));
    }
}
