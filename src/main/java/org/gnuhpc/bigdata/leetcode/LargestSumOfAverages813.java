package org.gnuhpc.bigdata.leetcode;

public class LargestSumOfAverages813 {
    public double largestSumOfAverages(int[] A, int K) {
        double[][] dp = new double[A.length + 1][K + 1];
        double[] sum = new double[A.length + 1];
        for(int i = 1;i <= A.length;i++){
            sum[i] = sum[i - 1] + A[i - 1];
            dp[i][1] = sum[i] / i;
        }
        for(int i = 1;i <= A.length;i++){
            for(int k = 2;k <= K;k++){
                for(int j = 0;j < i;j++){
                    dp[i][k] = Math.max(dp[i][k],dp[j][k - 1] + (sum[i] - sum[j]) / (i - j));
                }
            }
        }
        return dp[A.length][K];

    }

}
