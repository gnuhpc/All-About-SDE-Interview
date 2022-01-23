package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class Reverse7 {
    public int reverse(int x) {
        StringBuffer str = x >= 0 ? new StringBuffer(Integer.toString(x)) : new StringBuffer(Integer.toString(x).substring(1));
        try {
            return x >= 0 ? Integer.valueOf(str.reverse().toString()) : Integer.valueOf(str.append("-").reverse().toString());
        } catch (Exception e) {
            return 0;
        }
    }


    //Method2 : 直接操作数值
    public int reverse2(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }

    @Test
    public void test() {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
    }
}
