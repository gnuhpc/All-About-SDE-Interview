package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class MergeStones1000 {
    public int mergeStones(int[] stones, int K) {
        int N = stones.length;
        if((N-1)%(K-1)!=0) return -1;// 是否有解的前提条件
        //前缀和
        int[] preSum = new int[N+1];
        for(int i=1;i<=N;i++){
            preSum[i]=preSum[i-1]+stones[i-1];
        }
        int[][][] dp = new int[N][N][K+1]; //k能取到K 因故是K+1
        // init
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[i].length;j++){
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }
        for(int i=0;i<N;i++){
            dp[i][i][1]=0;// 注意是0哦
        }
        // 区间dp模板
        for(int len =2;len <= N; len++){ // sub problem length
            for(int i=0;i<=N-len;i++){
                int j = i+len-1;
                for(int k=2;k<=K;k++){
                    for(int m=i;m<j;m +=K-1){ // m 跳步应该是K-1,不应该用k-1;
                        dp[i][j][k]=Math.min(dp[i][j][k],dp[i][m][1]+dp[m+1][j][k-1]);
                    }
                    dp[i][j][1]=(preSum[j+1]-preSum[i])+dp[i][j][k];
                }
            }
        }
        return dp[0][N-1][1];
    }
}
