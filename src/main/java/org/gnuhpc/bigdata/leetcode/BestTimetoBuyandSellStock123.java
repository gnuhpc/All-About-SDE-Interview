package org.gnuhpc.bigdata.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestTimetoBuyandSellStock123 {
    // 错误解法，
    // test case [1,2,4,2,5,7,2,4,9,0]
    // 因为受限于最多两次条件，所以分阶段然后取最大的2个阶段不适用了
    // 列在此处，为了展示最大堆的用法
    private static final int DEFAULT_INITIAL_CAPACITY = 2;
    PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int maxProfit = 0;
        int i = 1;
        int buy = 0;
        int sell = 0;
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        while (i < prices.length) {
            while (i < prices.length && prices[i - 1] >= prices[i]) {
                i++;
            }
            buy = i - 1;
            while (i < prices.length && prices[i - 1] <= prices[i]) {
                i++;
            }
            sell = i - 1;
            maxHeap.add(prices[sell] - prices[buy]);
        }
        if(null != maxHeap.peek()) maxProfit += maxHeap.poll();
        if(null != maxHeap.peek()) maxProfit += maxHeap.poll();
        return maxProfit;
    }


    // 正解，动态规划dp九章，仅作记录，后续理解
    public int maxProfit2(int[] prices) {
        // write your code here
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int [][]dp = new int[n + 1][5 + 1];

        for (int k = 1; k <= 5; k++) {
            dp[0][k] = Integer.MIN_VALUE;
        }
        dp[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            // 手中未持有股票
            for (int j = 1; j <= 5; j += 2) {
                // 前一天也未持有
                dp[i][j] = dp[i - 1][j];
                if (j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    //                            前一天持有，今天卖了获利。
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // 手中持有股票
            for (int j = 2; j <= 5; j += 2) {
                //前一天未持有，今天买进
                dp[i][j] = dp[i - 1][j - 1];
                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    //                            前一天持有了，计算今天的利润
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }

        int res = 0;
        for (int j = 1; j <= 5; j += 2) {
            res = Math.max(res, dp[n][j]);
        }
        return res;
    }


    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int[][] dp = new int[prices.length + 1][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], prices[1] - prices[0]);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);

        if (prices.length == 2) {
            return dp[1][0];
        }

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }


}
