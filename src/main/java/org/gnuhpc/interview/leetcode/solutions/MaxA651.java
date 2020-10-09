package org.gnuhpc.interview.leetcode.solutions;

public class MaxA651 {
    int[] memo;
    public int maxA(int N) {
        if(N <=4) return N;
        memo = new int[N+1];
        return dfs(N);
    }

    private int dfs(int N){
        if(N<0) return -1;
        if(memo[N]!=0) return memo[N];
        int res = N;
        for(int i = 1; i<=4;i++)
            res = Math.max(res,(1+i)*dfs(N-2-i));
        memo[N] = res;
        return res;
    }
}
