package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/9
 */
public class NmWays10 {
    public int numWays(int n) {
        //本题中0个台阶是1种跳法：即--不跳
        if (n == 0 || n == 1) return 1;
        //法一：内存复用的过程转移，节省空间
        int a = 1;
        int b = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            //在循环的过程中即进行取模处理，以防止大数溢出
            res = (a + b) % 1000000007;
            a = b;
            b = res;
        }
        return res;
        //法二：使用数组存储每步的数据，浪费空间
        // int[] dp = new int[n + 1];
        // dp[0] = 1;
        // dp[1] = 1;
        // //使用数组占用内存大
        // for(int i = 2; i <= n; i++){
        //     dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        // }
        // return dp[n];
    }
}
