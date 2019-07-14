package org.gnuhpc.bigdata.leetcode;

//花花讲的非常好：https://www.youtube.com/watch?v=OZX1nqaQ_9M
//思考的出发点是从1的字符开始两边扩展
// 演示：https://docs.google.com/presentation/d/1KhxVVgI8jzc-g7unDNKFiHY6XDNVSK6LNsadxB14K3U/edit#slide=id.g4dab135aa1_0_468
public class LongestPalindromeSubseq516 {
    int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][]dp = new int[n][n];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;//basecase
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //case 2
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    //case 1
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
