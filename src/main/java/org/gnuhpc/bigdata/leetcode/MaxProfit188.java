package org.gnuhpc.bigdata.leetcode;

public class MaxProfit188 {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length <=1 || k == 0) return 0;

        int n = prices.length;
        int max_k = k;

        if(k>n/2) return maxProfit(prices);
        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < k+1; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }


        dp[1][1][0] = Math.max(dp[0][1][0], dp[0][1][1] + prices[1]);
        dp[1][1][1] = Math.max(dp[0][1][1], -prices[1]);

        if(k>=2) {
            dp[1][2][0] = Math.max(dp[0][2][0], dp[0][2][1] + prices[1]);
            dp[1][2][1] = Math.max(dp[0][2][1], dp[0][1][0] - prices[1]);
        }



        for (int i = 1; i < n; i++) {
            for (int j = max_k; j >= 1; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }

    private int maxProfit(int[] prices) {
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
