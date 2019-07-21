package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 19-7-15
 */
public class MaxProfit309 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <=1) return 0;

        int[][] dp = new int[prices.length+1][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0],prices[1]-prices[0]);
        dp[1][1] = Math.max(dp[0][1],dp[0][0] - prices[1]);

        if (prices.length == 2){
            return dp[1][0];
        }

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] -prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
