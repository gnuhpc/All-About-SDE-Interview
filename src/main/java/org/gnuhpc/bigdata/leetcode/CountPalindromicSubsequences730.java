package org.gnuhpc.bigdata.leetcode;

public class CountPalindromicSubsequences730 {
    private int[][] memo;
    private static final int kMod = 1000000007;
    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-730-count-different-palindromic-subsequences/
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        memo = new int[n][n];
        return count(S.toCharArray(), 0, n - 1);
    }

    private int count(char[] s, int i, int j) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] > 0) return memo[i][j];

        // 根据题意，count结果可能很大，用long更保险
        long ans = 0;

        if (s[i] == s[j]) {
            //  count(中间子串解) *2
            ans += count(s, i + 1, j - 1) * 2;
            int l = i + 1;
            int r = j - 1;
            //收尾相同的有三种情况
            while (l <= r && s[l] != s[i]) ++l;
            while (l <= r && s[r] != s[i]) --r;
            // case1: 中间子串的字符中，都不同于当前首尾字符
            if (l > r) ans += 2;
            // case2：中间子串的字符中，有1个字符等于当前首尾字符
            else if (l == r) ans += 1;
            // case3: 中间子串的2个首尾字符，等于当前首尾字符
            else ans -= count(s, l + 1, r - 1);
        } else {
            ans = count(s, i, j - 1)
                    + count(s, i + 1, j)
                    - count(s, i + 1, j - 1);
        }

        memo[i][j] = (int)((ans + kMod) % kMod);
        return memo[i][j];
    }
}
