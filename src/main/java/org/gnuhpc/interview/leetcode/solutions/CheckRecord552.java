package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/8/4
 */
public class CheckRecord552 {
    public int checkRecord(int n) {
        return dfs(n, 1, 2, new int[n + 1][2][3]);
    }

    private int dfs(int n, int a, int l, int[][][] cache) {
        if (n == 0) return 1;
        if (cache[n][a][l] != 0) return cache[n][a][l];
        final int MOD = 1000000007;
        int ans = 0;
        // A L P
        if (a > 0) ans = (ans + dfs(n - 1, a - 1, 2, cache)) % MOD;
        if (l > 0) ans = (ans + dfs(n - 1, a, l - 1, cache)) % MOD;
        ans = (ans + dfs(n - 1, a, 2, cache)) % MOD;
        return cache[n][a][l] = ans;
    }
}
