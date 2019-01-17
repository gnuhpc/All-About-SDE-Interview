package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class FindNthDigit400 {
    int findNthDigit(int n) {
        int len = 1, base = 1;  // len表示当前数的位数, base表示当前位是个位、百位、千位等...
        while (n > 9L * base * len) {
            n -= 9 * base * len;
            len++;
            base *= 10;
        }
        int curNum = (n - 1)/len + base, digit = 0;   // curNum是含有所找digit的那个数
        for (int i = (n - 1) % len; i < len; ++i) {          // 根据偏移量找到所找的数字
            digit = curNum % 10;
            curNum /= 10;
        }
        return digit;
    }

    @Test
    public void test(){
        findNthDigit(18);
    }
}
