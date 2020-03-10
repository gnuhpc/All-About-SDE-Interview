package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class IsPowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        while (n != 1) {
            if (n % 2 != 0) return false;
            n = n / 2;
        }

        return true;

    }


    @Test
    public void test() {
        System.out.println(isPowerOfTwo(14));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(1024));
        System.out.println(isPowerOfTwo(218));
    }
}
