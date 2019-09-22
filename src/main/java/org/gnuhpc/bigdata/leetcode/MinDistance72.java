package org.gnuhpc.bigdata.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//https://www.javacodegeeks.com/2014/03/easy-to-understand-dynamic-programming-edit-distance.html
public class MinDistance72 {
    /*
    Method 1: Recursive LTE
     */
    public int minDistance1(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        if (word1.equals(word2)) return 0;
        int replace = minDistance1(word1.substring(1), word2.substring(1)) + replaceCost(word1, word2, 0, 0);
        int delete = minDistance1(word1.substring(1), word2) + 1;
        int insert = minDistance1(word1, word2.substring(1)) + 1;
        return min(replace, delete, insert);
    }

    private int replaceCost(String w1, String w2, int w1Index, int w2Index) {
        return (w1.charAt(w1Index) == w2.charAt(w2Index)) ? 0 : 1;
    }

    private int min(int... numbers) {
        int result = Integer.MAX_VALUE;
        for (int each : numbers) {
            result = Math.min(result, each);
        }
        return result;
    }


    /*
    Method 2: Recursive Memorization almost pass LTE
     */

    public int minDistance(String word1, String word2) {
        Map<String, Integer> memo = new HashMap<>();
        return getdis(word1,word2,memo);
    }

    private int getdis(String word1, String word2, Map<String, Integer> memo) {
        String key = word1+"_"+word2;
        if (word1.isEmpty()) {
            memo.put(key,word2.length());
            return word2.length();
        }
        if (word2.isEmpty()) {
            memo.put(key,word1.length());
            return word1.length();
        }
        if (word1.equals(word2)) {
            memo.put(key,0);
            return 0;
        }
        if (memo.containsKey(key)) return memo.get(key);

        int replaceCost = replaceCost(word1, word2, 0, 0);
        int replace = getdis(word1.substring(1), word2.substring(1),memo) + replaceCost;
        int delete = getdis(word1.substring(1), word2, memo) + 1;
        int insert = getdis(word1, word2.substring(1),memo) + 1;
        int res = min(replace, delete, insert);
        memo.put(key,res);
        return res;
    }

    /*
    Method 3 : DP
     */
    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m==0) return n;
        if(n==0) return m;
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) dp[i][0] = i;
        for(int j=0;j<=n;j++) dp[0][j] = j;
        for(int i=1;i<m+1;i++)
            for(int j=1;j<n+1;j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j],dp[i][j-1])+1;
            }
        return dp[m][n];
    }

}
