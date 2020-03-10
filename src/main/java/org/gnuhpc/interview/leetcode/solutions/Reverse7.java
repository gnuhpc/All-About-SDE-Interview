package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class Reverse7 {
    public int reverse(int x) {
        int sign = 1;
        int idx = 0;

        String strX = String.valueOf(x);

        if (strX.charAt(idx) == '-') {
            sign = -1;
            idx = 1;
        }

        char[] temp = strX.substring(idx).toCharArray();
        for (int i = 0, j = strX.substring(idx).length() - 1; i < j; i++, j--) {
            Utils.swap(temp, i, j);
        }

        long res = Long.valueOf(String.valueOf(temp)) * sign;

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            res = 0;
        }

        return (int) res;
    }

    //Method2 : 直接操作数值
    public int reverse2(int x) {
        long res = 0;
        for (; x != 0; x /= 10) {
            res = res * 10 + x % 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

    @Test
    public void test() {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
    }
}
