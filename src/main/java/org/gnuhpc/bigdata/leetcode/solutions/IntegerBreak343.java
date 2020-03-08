package org.gnuhpc.bigdata.leetcode.solutions;

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
        if (n > maxProduct) { //还有一个选择是不拆，这个函数与题目主函数的区别就在这儿
            return n;
        }
        return maxProduct;
    }

    //add by tina 自顶向下记忆化搜索方法，标准写法
    // 理解清楚split和memo的含义，不要将memo放到for循环中判断
    private int[] memo;

    public int integerBreak2(int n) {
        if (n <= 1) return -1;
        memo = new int[n + 1];   //任意乘积不会到达0，所以按默认值赋值即可
        return split(n);
    }

    // 将num进行分割（至少分割成2部分），可以获得最大的乘积
    public int split(int num) {
        if (num == 1) return 1;
        if (memo[num] != 0) return memo[num];

        int max = 0;
        for (int i = 1; i < num; i++) {
            //此处有陷阱，原本我的写法是
            // max = Math.max(max,i*split(num-i,memo))
            // 这样会把num-i不拆的情况，即i*(num-i)漏掉
            max = Math.max(i * (num - i), Math.max(max, i * split(num - i)));
        }
        memo[num] = max;
        return max;
    }

    public int integerBreak3(int n) {
        if (n <= 1) return -1;
        memo = new int[n + 1];   //任意乘积不会到达0，所以按默认值赋值即可
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = Math.max(j * (i - j), Math.max(memo[i], j * memo[i - j]));
            }
        }
        return memo[n];
    }


    @Test
    public void test() {
        integerBreak(10);
    }
}
