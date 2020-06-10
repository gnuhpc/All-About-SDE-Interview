package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isPalindrome9 {
    /*
    Method1 :先计算位数
     */

    public boolean isPalindrome(int num) {
        if (num < 0) return false;
        /*find the most significant digit*/
        int div = 1;
        while (num / div >= 10) div *= 10; // two digits

        while (num != 0) { // stop till num is 0, all digits compared
            int l = num / div; // left digit
            int r = num % 10; // right digit
            if (l != r) return false; // compare
            num = (num % div) / 10; // remove first and last digit
            div /= 100; // div should be smaller
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int[] arr = new int[32];
        int n = 0;
        while (x != 0) {
            arr[n++] = x % 10;
            x = x / 10;
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != arr[n - i - 1])
                return false;
        }
        return true;
    }


    // 逆序
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        // 保存逆序结果
        int temp = 0;
        int number = x;
        while (number > 0) {
            temp = temp * 10 + number % 10;
            number = number / 10;
        }
        return x == temp;
    }

    @Test
    public void test() {
        assertFalse(isPalindrome(-10));
        assertTrue(isPalindrome(0));
        assertTrue(isPalindrome2(101));
        assertFalse(isPalindrome(10));
        assertTrue(isPalindrome(930039));
        assertFalse(isPalindrome(1000021));
    }
}
