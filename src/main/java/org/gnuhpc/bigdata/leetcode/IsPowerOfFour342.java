package org.gnuhpc.bigdata.leetcode;

public class IsPowerOfFour342 {
    public boolean isPowerOfFour(int num) {
        if (num<=0) return false;

        //Filter 2,8,...

        int res = num & (num-1);
        int filter = num & 0x55555555;
        return (res==0 && filter!=0);
    }
}
