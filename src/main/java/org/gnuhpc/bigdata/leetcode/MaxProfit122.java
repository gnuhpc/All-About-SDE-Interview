package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 19-7-15
 */

//From https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
public class MaxProfit122 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length ==0) return 0;
        int[][] dp = new int[prices.length+1][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] -prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
