package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class CoinChange322 {

        /*
    Method1 :Recursive  (LTE)

    for each coin, if I take that coin into account,
    then the fewest number of coins we can get is 1+coinChange(amount-that_coin_value).
    So for all the coins, we return the smallest number as min(1+coinChange(amount-coin1_value),
    1+coinChange(amount-coin2_value, ......).
     */

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange1(coins, amount - coin);
                if (next >= 0)
                    curr = 1 + next;
            }
            if (curr > 0)
                res = Math.min(res, curr);
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    /*
    Recursive + Memorization

    Then we observed that this algorithm may compute coinChange of same amount for many times,
    which are kind of duplicate,
    if we can store "amount->fewest_coin_count" into hashtble,
    then we don't need to recompute again.
     */
    private int[] memo;

    public int coinChange2(int[] coins, int amount) {
        memo = new int[amount + 1];
        return solve(coins, amount);
    }

    public int solve(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (memo[amount] != 0)
            return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = solve(coins, amount - coin);
                if (next >= 0)
                    curr = 1 + next;
            }
            if (curr > 0)
                res = Math.min(res, curr);
        }
        int finalCount = (res == Integer.MAX_VALUE) ? -1 : res;
        memo[amount] = finalCount;
        return finalCount;
    }

    /*
    DP
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }

        int dp[] = new int[amount + 1]; //dp[x] 为拼出x需要的最少硬币数
        Arrays.fill(dp, Integer.MAX_VALUE);//不能拼出就记为正无穷 ,因为要求min
        dp[0] = 0;

        for (int x = 0; x < dp.length; x++) {
            for (int coin : coins) {
                if (x - coin >= 0 && dp[x - coin] != Integer.MAX_VALUE) {
                    dp[x] = Math.min(dp[x], dp[x - coin] + 1);
                }
            }
        }
        // 按题意 -1 表示拼不出来
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    @Test
    public void test() {
        System.out.println(coinChange1(new int[]{5, 4, 3}, 11));
    }

}
