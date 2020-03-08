package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/2/12
 */
public class FindMaxForm474 {
    /*
    Method1: recursive solution (LTE)
     */

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        return tryFindMaxForm(strs, strs.length - 1, m, n);
    }

    // 用m，n 拼出 strs[0,i] 的 最大个数
    public int tryFindMaxForm(String[] strs, int i, int m, int n) {
        if (i < 0) {
            return 0;
        }
        int numsOf0 = 0;
        int numsOf1 = 0;
        String str = strs[i];
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '0') {
                numsOf0++;
            }
            else {
                numsOf1++;
            }
        }
        if (m >= numsOf0 && n >= numsOf1) {
            return Math.max(tryFindMaxForm(strs, i - 1, m, n),
                            1 + tryFindMaxForm(strs, i - 1, m - numsOf0, n - numsOf1));
        }
        else {
            return tryFindMaxForm(strs, i - 1, m, n);
        }
    }

    /*
    Method2: memo + dfs
     */

    private int[][][] memo;

    public int findMaxForm2(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        this.memo = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return tryFindMaxForm(strs, strs.length - 1, m, n);
    }

    // 用m，n 拼出 strs[0,i] 的 最大个数
    public int tryFindMaxForm2(String[] strs, int i, int m, int n) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][m][n] != -1) {
            return memo[i][m][n];
        }
        int numsOf0 = 0;
        int numsOf1 = 0;
        String str = strs[i];
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '0') {
                numsOf0++;
            }
            else {
                numsOf1++;
            }
        }
        if (m >= numsOf0 && n >= numsOf1) {
            memo[i][m][n] = Math.max(tryFindMaxForm2(strs, i - 1, m, n),
                                     1 + tryFindMaxForm2(strs, i - 1, m - numsOf0, n - numsOf1));
        }
        else {
            memo[i][m][n] = tryFindMaxForm2(strs, i - 1, m, n);
        }
        return memo[i][m][n];
    }

    /*
    Method3: DP
     */

    public int findMaxForm3(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }
        // dp[i][j][k] 表示j个0，k个1组成s[0...i]的最大个数，默认0
        int[][][] dp = new int[strs.length][m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            int numsOf0 = 0;
            int numsOf1 = 0;
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    numsOf0++;
                }
                else {
                    numsOf1++;
                }
            }
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= numsOf0 && k >= numsOf1) {
                        if (i == 0) {
                            dp[i][j][k] = 1;
                        }
                        else {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], 1 + dp[i - 1][j - numsOf0][k - numsOf1]);
                        }
                    }
                    else {
                        dp[i][j][k] = i > 0 ? dp[i - 1][j][k] : 0;
                    }
                }
            }
        }
        return dp[strs.length - 1][m][n];
    }

    /*
    Method4: DP Rolling Array
     */

    public int findMaxForm4(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            int numsOf0 = 0;
            int numsOf1 = 0;
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    numsOf0++;
                }
                else {
                    numsOf1++;
                }
            }
            for (int j = m; j >= numsOf0; j--) {
                for (int k = n; k >= numsOf1; k--) {
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - numsOf0][k - numsOf1]);
                }
            }
        }
        return dp[m][n];
    }

    /*
    链接：https://leetcode-cn.com/problems/ones-and-zeroes/solution/zi-ding-xiang-xia-ji-yi-sou-suo-zi-di-xiang-shang-/
    来源：力扣（LeetCode）
    */


}
