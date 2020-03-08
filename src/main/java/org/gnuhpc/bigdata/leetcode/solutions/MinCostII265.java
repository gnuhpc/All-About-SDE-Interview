package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class MinCostII265 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length, m = costs[0].length;

        //dp[i][j] indicate the optimal cost for the house i if it is painted with color j.
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++)
            dp[0][j] = costs[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = minCost(dp[i - 1], j) + costs[i][j];
            }
        }
        return minCost(dp[n - 1], -1);
    }

    //find the minimum cost if the current house is painted with different color except j.
    //if j == -1, then find minimum cost for the current house comparing different color.
    private int minCost(int[] dp, int j) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (j != -1 && i == j) ;
            else
                min = Math.min(min, dp[i]);
        }
        return min;
    }

    @Test
    public void test() {
        System.out.println(minCostII(new int[][]{
                {8}
        }));
    }

}
