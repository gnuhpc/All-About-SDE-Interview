package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

//DONE
public class LongestPalindrome5 {
    @Test
    public void test() {
        String s = "a";
        System.out.println(longestPalindrome2(s));
    }


    /**
     * O(n^2) Time, O(1) Space
     * Expand from center character and center of two chars
     * Update result according to the returned length
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            //求出奇数点为中心的情况
            String s1 = expandAroundCenter(s, i, i);
            if (s1.length() > longest.length()) longest = s1;
            //如果前后两个相等，则求出偶数点为中心的情况
            if (s.charAt(i) == s.charAt(i + 1)) {
                String s2 = expandAroundCenter(s, i, i + 1);
                if (s2.length() > longest.length()) longest = s2;
            }
        }
        return longest;
    }

    /**
     * Search for range in both direction
     */

    private String expandAroundCenter(String s, int i, int j) {
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1  都是闭区间
    }

    /*
    Method2 : DP
    ## Time complexity
    `O(n^2)` since we have to fill the 2D array.

    ## Space complexity
    `O(n^2)` since we store information in the 2D array.
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        String ret = "";
        char[] sCh = s.toCharArray();
        int len = s.length();
        boolean[][] dp = new boolean[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {//为什么从后往前是因为递归公式中需要i+1的结果
            for (int j = i; j < len; j++) {
                if (i == j) dp[i][j] = true; //base case 1
                else if (j == i + 1) dp[i][j] = (sCh[i] == sCh[j]);//base case 2
                else //递推公式
                    dp[i][j] = (sCh[i] == sCh[j]) ? dp[i + 1][j - 1] : false;

                if (dp[i][j] && (j - i + 1 > ret.length())) {//获取最长的回文子串
                    ret = s.substring(i, j + 1);
                }
            }
        }
        return ret;
    }


}
