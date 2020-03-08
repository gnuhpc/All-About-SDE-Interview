package org.gnuhpc.bigdata.leetcode.solutions;

public class MinCostClimbingStairs746 {

    // 要爬到最顶层，必须要跨越第cost.length阶
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        if (n == 2) return Math.min(cost[0], cost[1]);

        int[] dp = new int[n + 1]; //the cost needed to get to floor (remember: not step) i
        // 因为可以选择从第1阶或第2阶开始爬，所以爬到第1阶或第2阶需要的最小体力皆为0
        dp[0] = 0;
        dp[1] = 0;
        // 爬到第i阶，可以选择从第i - 2阶爬2阶上来，或者选择从第i-1阶爬1阶上来
        for (int i = 2; i <= n; i++) { // 计算爬到第i + 1阶需要的最小体力
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n]; // 返回爬到楼层顶部的最小体力
    }

}
