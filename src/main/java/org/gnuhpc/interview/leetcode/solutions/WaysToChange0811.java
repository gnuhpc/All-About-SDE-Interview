package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/23
 */
public class WaysToChange0811 {
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] weight = {1, 5, 10, 25};
        for (int i = 0; i < 4; i++) {       // 物品
            for (int j = weight[i]; j <= n; j++) {  // 剩余容量
                dp[j] = (dp[j] + dp[j - weight[i]]) % 1000000007;
            }
        }
        return dp[n];
    }
}
