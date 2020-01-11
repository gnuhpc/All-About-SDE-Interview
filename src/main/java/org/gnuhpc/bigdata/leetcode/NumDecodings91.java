package org.gnuhpc.bigdata.leetcode;


import java.util.Arrays;

public class NumDecodings91 {

    /*
    DP
    `dp[0]` means an empty string will have one way to decode,
    `dp[1]` means the way to decode a string of size 1.
     I then check one digit and two digit combination
     and save the results along the way.
     In the end, `dp[n]` will be the end result.
     */

    public int numDecodings2(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i-1, i));
            int second = Integer.parseInt(s.substring(i-2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //memo search,类似于有条件的climb stairs
    int[] memo;

    public int numDecodings(String s) {
        memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return dfs(s, 0);
    }

    public int dfs(String s, int num) {

        if (num == s.length()) {
            return 1;
        }
        if (s.charAt(num) == '0') {
            return 0;
        }
        if (memo[num] > -1) {
            return memo[num];
        }
        int ans1 = dfs(s, num + 1);


        int ans2 = 0;
        if (num < s.length() - 1) {
            // 判断前两个数字是否  >26？
            if ((s.charAt(num) - '0') * 10 + (s.charAt(num + 1) - '0') <= 26) {
                ans2 = dfs(s, num + 2);
            }
        }

        memo[num] = ans1 + ans2;
        return memo[num];
    }
}
