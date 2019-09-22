package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class FindNthDigit400 {
    /*
    Straight forward way to solve the problem in 3 steps:
    1. find the length of the number where the nth digit is from
    2. find the actual number where the nth digit is from
    3. find the nth digit and return
     */
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Integer.parseInt(s.charAt((n-1)%len)+"");
    }


    @Test
    public void test(){
        findNthDigit(18);
    }
}
