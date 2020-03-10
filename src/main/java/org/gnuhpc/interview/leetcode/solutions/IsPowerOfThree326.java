package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class IsPowerOfThree326 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 3 == 0) {
            return isPowerOfThree(n / 3);
        } else {
            return false;
        }
    }

    //Without recursion or loop
    public boolean isPowerOfThree2(int n) {
        if (n <= 0) return false;

        int left = 1, right = n / 3;

        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            double temp = Math.pow(3, mid);

            if (temp == n) return true;
            else if (temp > n) right = mid;
            else left = mid;
        }

        if (Math.pow(3, left) == n) return true;
        if (Math.pow(3, right) == n) return true;

        return false;
    }


    @Test
    public void test() {
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(59));
    }
}
