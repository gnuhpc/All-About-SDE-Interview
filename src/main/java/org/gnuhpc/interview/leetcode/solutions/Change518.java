package org.gnuhpc.interview.leetcode.solutions;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2020/9/3
 */
public class Change518 {
    public int change(int amount, int[] coins) {
        // order coins in order to prune recursion
        Arrays.sort(coins);
        // init memorization to -1 (unvisited)
        int[][] memo = new int[amount + 1][coins.length];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return dfs(coins, amount, 0, memo);
    }

    private int dfs(int[] coins, int amount, int index, int[][] memo) {
        if (amount == 0) return 1;
        if (index == coins.length) return 0;
        if (memo[amount][index] != -1) return memo[amount][index];

        int cnt = 0;
        for (int i = index; i < coins.length; i++) {
            if (coins[i] > amount) break;
            // using this coin as many times as possible before going to next coin
            int count = 1;
            while (count * coins[i] <= amount) {
                cnt += dfs(coins, amount - count * coins[i], i + 1, memo);
                count++;
            }
        }

        // memorize
        memo[amount][index] = cnt;
        return cnt;
    }


    @Test
    public void test() {
        System.out.println(change(5, new int[]{1, 2, 5}));
    }
}
