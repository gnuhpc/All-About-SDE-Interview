package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class CountSubstrings647 {
    /*
    Method1: 中心扩展法
     */
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            //分奇偶考虑
            res += countSegment(s, i, i);
            if(i<s.length()-1 && s.charAt(i) == s.charAt(i+1))
                res += countSegment(s, i, i + 1);
        }
        return res;
    }

    //start往左边跑，end往右边跑, 判断s[start, end]是否为回文
    public int countSegment(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++))
            count++;
        return count;
    }

    /*
    Method2: DP
     */
    public int countSubstrings2(String s) {
        int result = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        char[] sCh = s.toCharArray();

        for (int i = s.length()-1; i >=0 ; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i==j)
                    dp[i][j] = true;
                else if(j==i+1)
                    dp[i][j] = (sCh[i] == sCh[j]);
                else
                    dp[i][j] = (sCh[i] == sCh[j])? dp[i + 1][j - 1]:false;
                if (dp[i][j]) result++;
            }
        }

        return result;
    }
}
