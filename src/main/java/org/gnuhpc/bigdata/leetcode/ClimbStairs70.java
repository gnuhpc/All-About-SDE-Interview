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

    // add by tina,DP的思想，即将上面自顶向下的思维方式，改成自底向上的方法来实现。
    public int climbStairs2(int n) {
        int[] memo = new int[n+1];//注意边界情况考虑，因为memo[n]不太可能为0
        memo[0] = 1;
        memo[1] = 1;
        for(int i = 2; i<=n;i++){
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }


    @Test
    public void test(){
        System.out.println(climbStairs(2));
    }
}
