package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/5/4
 */
public class MinCost1473 {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int ans = solve(target, houses, cost, 0, 0, -1, new Integer[houses.length][target + 1][cost[0].length + 2]);
        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    public int solve(int target, int[] houses, int[][] cost, int houseNo, int neighbourhoods, int prevColor, Integer[][][] dp) {

        if (neighbourhoods > target) {
            return Integer.MAX_VALUE / 2;
        }

        if (houseNo == houses.length) {
            if (neighbourhoods < target) {
                return Integer.MAX_VALUE / 2;
            }
            // else number of neighbourhoods == target, and covered all houses
            return 0;
        }

        if (dp[houseNo][neighbourhoods][prevColor + 1] != null)
            return dp[houseNo][neighbourhoods][prevColor + 1];

        // if house is already coloured,  no need to update cost or recoloring, just update number of neighbourhoods accordingly.
        if (houses[houseNo] != 0) {
            return dp[houseNo][neighbourhoods][prevColor + 1] = solve(target, houses, cost, houseNo + 1, (houses[houseNo] == prevColor ? neighbourhoods : neighbourhoods + 1), houses[houseNo], dp);
        }
        // if house is not coloured, update cost, color and number of neighbourhoods
        int min = Integer.MAX_VALUE / 2;
        for (int i = 0; i < cost[0].length; i++) {

            min = Math.min(min, cost[houseNo][i] + solve(target, houses, cost, houseNo + 1,
                    (i + 1 == prevColor ? neighbourhoods : neighbourhoods + 1), i + 1, dp));

        }
        return dp[houseNo][neighbourhoods][prevColor + 1] = min;
    }
}
