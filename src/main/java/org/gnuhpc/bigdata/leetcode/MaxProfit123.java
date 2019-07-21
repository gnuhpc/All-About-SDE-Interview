package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 19-7-15
 */
public class MaxProfit123 {
    public int maxProfit(int[] prices) {
        if(prices == null | prices.length <=1) return 0;

        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];

        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];


        dp[1][2][0] = Math.max(dp[0][2][0], dp[0][2][1] + prices[1]);
        dp[1][2][1] = Math.max(dp[0][2][1], dp[0][1][0] - prices[1]);
        dp[1][1][0] = Math.max(dp[0][1][0], dp[0][1][1] + prices[1]);
        dp[1][1][1] = Math.max(dp[0][1][1], -prices[1]);



        for (int i = 1; i < n; i++) {
            for (int j = max_k; j >= 1; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }
}
