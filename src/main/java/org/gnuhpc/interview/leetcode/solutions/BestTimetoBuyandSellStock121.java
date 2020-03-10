package org.gnuhpc.interview.leetcode.solutions;

public class BestTimetoBuyandSellStock121 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int minValue = Integer.MAX_VALUE;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            minValue = Math.min(minValue, prices[i]);
            res = Math.max(res, prices[i] - minValue);
        }
        return res;
    }


    //Method2: 通用DP
    /*
    https://www.cnblogs.com/hanyuhuang/p/11083384.html
    dp[i][k][0 or 1]
    0 <= i <= n-1, 1 <= k <= K
    n 为天数，大 K 为最多交易数
    此问题共 n × K × 2 种状态，全部穷举就能搞定。

    for 0 <= i < n:
        for 1 <= k <= K:
            for s in {0, 1}:
                dp[i][k][s] = max(buy, sell, rest)
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        //k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];

    }
}
