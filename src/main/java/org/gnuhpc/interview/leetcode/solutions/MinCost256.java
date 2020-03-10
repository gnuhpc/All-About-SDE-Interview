package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import static org.gnuhpc.interview.leetcode.utils.Utils.min;

public class MinCost256 {
    public int minCost(int[][] costs) {
        int length = costs.length;
        if (length == 0) return 0;
        int dp[][] = new int[length][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        //当前的房子所花费最小费用
        //如果当前为红色，那么上一个只能是另外两种颜色，加上其中的最小值即
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return min(dp[length - 1][0], dp[length - 1][1], dp[length - 1][2]);
    }

    @Test
    public void test() {
        System.out.println(minCost(new int[][]{
                {17, 2, 17}, {16, 16, 5}, {14, 3, 19}
        }));
    }

}
