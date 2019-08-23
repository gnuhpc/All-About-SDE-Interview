package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class IsPowerOfTwo231 {
    //二分套路
    public boolean isPowerOfTwo(int n) {
        if (n<=0) return false;

        int left = 1, right = n/2;

        while (left+1<right){
            int mid = (right-left)/2 + left;
            double temp = Math.pow(2,mid);

            if (temp == n) return true;
            else if (temp>n) right = mid;
            else left = mid;
        }

        if (Math.pow(2,left) == n) return true;
        if (Math.pow(2,right) == n) return true;

        return false;
    }


    @Test
    public void test(){
        System.out.println(isPowerOfTwo(14));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(1024));
        System.out.println(isPowerOfTwo(218));
    }
}
