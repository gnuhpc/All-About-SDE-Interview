package org.gnuhpc.interview.leetcode.solutions;

public class BestTimetoBuyandSellStock122 {
    // 方法一
    public int maxProfit(int[] prices) {
        int sum = 0;
        int gap = 0;
        int mn = Integer.MAX_VALUE;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            mn = Math.min(mn, prices[i]);
            gap = Math.max(gap, prices[i] - mn);
            if (gap > prices[i] - mn) {
                mn = prices[i];
                sum += gap;
                gap = 0;
            }
        }
        //此处容易出错，因为最后一个阶段如果一直上升，gap不会被for中的if累加
        if (gap > 0) sum += gap;
        return sum;
    }

    // 方法二：思路与上面相同，但是更加好理解，代码更简洁高效
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int maxProfit = 0;
        int i = 1;
        int buy = 0;
        int sell = 0;
        while (i < prices.length) {
            while (i < prices.length && prices[i - 1] >= prices[i]) {
                i++;
            }
            buy = i - 1;
            while (i < prices.length && prices[i - 1] <= prices[i]) {
                i++;
            }
            sell = i - 1;
            maxProfit += prices[sell] - prices[buy];
        }
        return maxProfit;
    }


    //Method3 : 通用DP
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
