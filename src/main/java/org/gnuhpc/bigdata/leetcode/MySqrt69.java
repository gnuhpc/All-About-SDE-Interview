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
        int end = x/2;

        int mid;
        while (start +1 < end) { // can equal
            mid = start + (end - start) / 2; // left + right can overflow
            if (mid == x / mid) start = mid; // mid * mid can overflow, 找最后一个满足条件的，因此start = mid， 往后找
            else if (x/mid > mid) start = mid;
            else end = mid; // break equal
        }

        if (end <= x / end) return end;
        else return start;
    }


    @Test
    public void test(){
        System.out.println(mySqrt2(2147395600));
    }
}
