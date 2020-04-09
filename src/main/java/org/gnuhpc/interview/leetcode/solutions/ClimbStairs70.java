package org.gnuhpc.interview.leetcode.solutions;

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
        if (n < 3) return n;
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[0] = 1;
        mem[1] = 1;
        mem[2] = 2;

        return climb(mem, n);
    }

    private int climb(int[] mem, int n) {
        if (mem[n] != -1) {
            return mem[n];
        } else {
            int res = climb(mem, n - 1) + climb(mem, n - 2);
            mem[n] = res;
            return res;
        }
    }

    // DP，即将上面自顶向下的思维方式，改成自底向上的方法来实现。
    /*
本问题其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和

爬上 n-1n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶
爬上 n-2n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶
所以我们得到公式 dp[n] = dp[n-1] + dp[n-2]dp[n]=dp[n−1]+dp[n−2]
同时需要初始化 dp[0]=1dp[0]=1 和 dp[1]=1dp[1]=1
时间复杂度：O(n)O(n)
     */
    public int climbStairs2(int n) {
        if (n < 3) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    @Test
    public void test() {
        System.out.println(climbStairs(2));
    }
}
