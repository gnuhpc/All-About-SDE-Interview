package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class MySqrt69 {
    /*
    Method1 : 递推
     */
    public int mySqrt(int x) {
        if (x <= 1) return x;
        long i = 1;
        for (; i * i <= x; i++) ;

        return (int) i - 1;
    }

    /*
    Method2 : 二分
     */

    public int mySqrt2(int x) {
        if (x <= 1) return x;
        int start = 1;// search range
        int end = x;
        int mid;
        while (start +1 < end) { // can equal
            mid = start + (end - start) / 2; // left + right can overflow
            if (mid == x / mid) start = mid; // mid * mid can overflow
            else if (mid > x / mid) end = mid; // not right = mid
            else start = mid; // break equal
        }

        if (start*start<= x) return start;
        else return end;
    }

    @Test
    public void test(){
        System.out.println(mySqrt2(4));
    }
}
