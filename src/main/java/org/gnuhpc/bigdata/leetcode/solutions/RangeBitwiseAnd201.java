package org.gnuhpc.bigdata.leetcode.solutions;

public class RangeBitwiseAnd201 {
    public int rangeBitwiseAnd(int m, int n) {
        int r = Integer.MAX_VALUE;
        while ((m & r) != (n & r)) r = r << 1;
        return n & r;
    }
}
