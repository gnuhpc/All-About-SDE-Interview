package org.gnuhpc.bigdata.leetcode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//https://www.javacodegeeks.com/2014/03/easy-to-understand-dynamic-programming-edit-distance.html
public class MinDistance72 {
    /*
    Method 1: Recursive
     */
    public int minDistance1(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        int replace = minDistance1(word1.substring(1), word2.substring(1)) + replaceCost(word1, word2, 0, 0);
        int delete = minDistance1(word1.substring(1), word2) + 1;
        int insert = minDistance1(word1, word2.substring(1)) + 1;
        return min(replace, delete, insert);
    }


    /*
    Method 2: Recursive Memorization
     */

    public int minDistance(String word1, String word2) {
        int[][] dis = new int[word1.length()+1][word2.length()+1];
        return getdis(word1,word2,word1.length(),word2.length(),dis);

    }
    public static int getdis(String word1,String word2,int m,int n,int[][] dis){
        if(m==0) return n;
        if(n==0) return m;

        if(dis[m][n]>0)
            return dis[m][n];
        int temp;

        if(word1.charAt(m-1)==word2.charAt(n-1)){
            temp = getdis(word1,word2,m-1,n-1,dis);
        }else{
            temp = min(getdis(word1,word2,m-1,n-1,dis)+1,
                    getdis(word1,word2,m-1,n,dis)+1,
                    getdis(word1,word2,m,n-1,dis)+1);
        }
        dis[m][n] = temp;

        return dis[m][n];
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

    public static int replaceCost(String w1, String w2, int w1Index, int w2Index) {
        return (w1.charAt(w1Index) == w2.charAt(w2Index)) ? 0 : 1;
    }
    public static int min(int... numbers) {
        int result = Integer.MAX_VALUE;
        for (int each : numbers) {
            result = Math.min(result, each);
        }
        return result;
    }
}
