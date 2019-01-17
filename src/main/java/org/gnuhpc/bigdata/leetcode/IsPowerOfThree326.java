package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class IsPowerOfThree326 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n%3 == 0) {
            return isPowerOfThree(n/3);
        } else {
            return false;
        }
    }

    //Without recursion or loop
    public boolean isPowerOfThree2(int n) {
        int Max3PowerInt = 1162261467; // 3^19, 3^20 = 3486784401 > MaxInt32
        if (n <= 0 || n > Max3PowerInt) return false;
        return Max3PowerInt % n == 0;
    }

    @Test
    public void test(){
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(59));
    }
}
