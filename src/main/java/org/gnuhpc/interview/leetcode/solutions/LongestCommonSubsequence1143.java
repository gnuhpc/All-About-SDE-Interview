package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/26
 */
public class LongestCommonSubsequence1143 {
    private int[][] memo;//记忆化搜搜

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1 == "" || text2 == null || text2 == "") {
            return 0;
        }
        memo = new int[text1.length()][text2.length()];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] = -1;
            }
        }
        return lcs(text1, text2, 0, 0);
    }

    private int lcs(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        char a = text1.charAt(i);
        //System.out.println(ch1);
        char b = text2.charAt(j);
        //System.out.println(ch2);
        int res = 0;
        if (a == b) {
            res = 1 + lcs(text1, text2, i + 1, j + 1);
        } else {
            res = Math.max(lcs(text1, text2, i + 1, j), lcs(text1, text2, i, j + 1));
        }
        memo[i][j] = res;
        return res;
    }
}
