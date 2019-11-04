package org.gnuhpc.bigdata.leetcode;

import org.apache.derby.iapi.services.io.LimitInputStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright gnuhpc 19-7-11
 */
public class ClimbStairs70 {
    public int climbStairs(int n) {
        if(n<3) return n;
        int[] mem = new int[n+1];
        Arrays.fill(mem,-1);
        mem[0] = 0;
        mem[1] = 1;
        mem[2] = 2;

        return climb(mem,n);
    }

    private int climb(int[] mem, int n) {
        if (mem[n]!=-1) {
            return mem[n];
        } else {
            int res = climb(mem,n-1) + climb(mem,n-2);
            mem[n] = res;
            return res;
        }
    }

    // DP，即将上面自顶向下的思维方式，改成自底向上的方法来实现。
    public int climbStairs2(int n) {
        //注意边界情况考虑，因为memo[n]不太可能为0
        if (n<=0) return 0;
        if (n<=2) return n==1? 1: 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i<n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }


    @Test
    public void test(){
        System.out.println(climbStairs(2));
    }
}
