package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

/**
 * Copyright gnuhpc 19-7-14
 */

//From https://medium.com/@gepphkat/dynamic-programming-343-integer-break-4aa8dde3ee0f
public class IntegerBreak343 {
    private int[] memoTable;

    public int integerBreak(int n) {
        // init the memo table
        if (memoTable == null) {
            memoTable = new int[n + 1];
            memoTable[0] = 0;
            memoTable[1] = 0;
            memoTable[2] = 1;
            if (n > 2) {
                memoTable[3] = 2;
            }
        }
        if (memoTable[n] != 0) {
            return memoTable[n];
        }
        int greatestMax = -1;
        // iterate though each way we can break up n
        for (int i = 2; i <= n / 2; i++) {
            int n1 = i;
            int n2 = n - i;
            // for each number, get the max product n1 and n2 broken up
            int maxProduct1 = getMaxProductOfInt(n1);
            int maxProduct2 = getMaxProductOfInt(n2);
            int max = maxProduct1 * maxProduct2;
            if (max > greatestMax) {
                greatestMax = max;
            }
        }
        memoTable[n] = greatestMax;
        return greatestMax;
    }
    private int getMaxProductOfInt(int n) {
        int maxProduct = integerBreak(n);
        if (n > maxProduct) {
            return n;
        }
        return maxProduct;
    }


    @Test
    public void test(){
        integerBreak(10);
    }
}
