package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class NumTrees96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) { // i是总的节点数
            for (int j = 1; j <= i; j++) { // j是左子树的节点数
                dp[i] += dp[j - 1]*dp[i - j]; // j-1 + i - j  = i-1 为左右节点总数，加上根节点正好为i
            }
        }
        return dp[n];
    }
}
