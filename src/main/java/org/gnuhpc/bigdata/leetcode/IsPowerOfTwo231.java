package org.gnuhpc.bigdata.leetcode;

public class IsPowerOfTwo231 {
    public boolean isPowerOfTwo(int n) {
        if (n<=0) return false;
        int res = n & (n-1);
        return (res == 0);
    }
}
