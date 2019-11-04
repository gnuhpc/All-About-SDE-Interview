package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class LenLongestFibSubseq873 {
    //https://segmentfault.com/a/1190000016917461
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        int res = 0, sum;
        int[][] dp = new int[n][n];
        //dp[i][j]表示以A[i]、A[j]结尾的子序列的最大斐波那契数列长度
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 2);
        }
        int l = 0, r = 0;
        for(int i  = 1; i < n; i++) {
            l = 0;
            r = i - 1;
            while(l < r) {
                sum = A[l] + A[r];
                if(sum == A[i]) {
                    dp[r][i] = Math.max(dp[r][i], dp[l][r] + 1);
                    res = Math.max(dp[r][i], res);
                    l++;
                    r--;
                }else if(sum < A[i]) {
                    l++;
                }else {
                    r--;
                }
            }
        }
        return res;
    }

}
