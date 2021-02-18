package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/31
 */
public class WaysToStep0801 {
    public int waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long[] dp = new long[n + 1]; //dp[i]：存的是到 i 阶楼的所有方法总数
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007;//到达 i 阶的有三种方式，i-1 阶跨 1 步，i-2 阶跨 2 步，i-3 阶跨 3 步！
        }
        return (int) dp[n];
    }
}
