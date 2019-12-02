package org.gnuhpc.bigdata.leetcode;


import org.junit.Test;

//花花讲的非常好：https://www.youtube.com/watch?v=OZX1nqaQ_9M
//思考的出发点是从1的字符开始两边扩展
// 演示：https://docs.google.com/presentation/d/1KhxVVgI8jzc-g7unDNKFiHY6XDNVSK6LNsadxB14K3U/edit#slide=id.g4dab135aa1_0_468
//Follow up: https://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/
//Follow up2: LC 730
public class LongestPalindromeSubseq516 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // dp[i][j] is the max one from i to j (inclusive)
        for (int j = 0; j < n; j++) { //注意由memo改为dp的时候循环方向是从等式右边到等式左边
            // 比如此题中r的右边到左边为r-1 -> r，因此 j就是从0到n
            for (int i = j-1; i >=0 ; i--) {
                //比如此题中的l的右边到左边为l+1 -> l，因此 i就是j-1到0.
                if (i<0) continue; //注意判断边际条件
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    @Test
    public void test(){
        System.out.println(longestPalindromeSubseq("bbbab"));
    }

    /*
    Method2 : memorization
     */
    public int longestPalindromeSubseq2(String s) {
        memo = new int[s.length()][s.length()];
        for (int i = 0; i < memo.length; ++i) {
            for (int j = 0; j < memo.length; ++j) {
                memo[i][j] = -1;
            }
        }

        return helper(s, 0, s.length() - 1);
    }


    int[][] memo;

    private int helper(String s, int l, int r) { //从短字符串推出长字符串的方法
        if (l == r) return 1;
        if (l > r) return 0;

        if (memo[l][r] != -1) return memo[l][r];

        memo [l][r] = s.charAt(l) == s.charAt(r)?
                2 + helper(s, l + 1, r - 1)
                :
                Math.max(helper(s, l + 1, r), helper(s, l, r - 1));

        return memo[l][r];
    }
}
