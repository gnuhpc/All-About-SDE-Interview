package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/17
 */
public class MinimumOperations19 {
    //dp[i][j]代表从0 到i 字符串的j状态，j = 0 : 红 ,1： 红黄, 2： 红黄红。
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];// 0 : 红 1： 红黄 2： 红黄红
        dp[0][0] = leaves.charAt(0) == 'r' ? 0 : 1;
        dp[1][0] = dp[0][0] + (leaves.charAt(1) == 'r' ? 0 : 1);
        dp[1][1] = dp[0][0] + (leaves.charAt(1) == 'y' ? 0 : 1);
        dp[1][2] = 9999; // dp[1][2]没有实际意义，因为只有2个元素不可能是红黄红的，但是因为又要用到dp[1][2],所以设置下一个大值。
        for (int i = 2; i < len; i++) {
            dp[i][0] = dp[i - 1][0] + (leaves.charAt(i) == 'r' ? 0 : 1);
            dp[i][1] = Math.min(dp[i - 1][0] + (leaves.charAt(i) == 'y' ? 0 : 1), dp[i - 1][1] + (leaves.charAt(i) == 'y' ? 0 : 1));
            dp[i][2] = Math.min(dp[i - 1][1] + (leaves.charAt(i) == 'r' ? 0 : 1), dp[i - 1][2] + (leaves.charAt(i) == 'r' ? 0 : 1));
        }
        return dp[len - 1][2];

    }
}
