package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CoinChange322 {

    /*
    Method1 :Recursive  (LTE)

    for each coin, if I take that coin into account,
    then the fewest number of coins we can get is 1+coinChange(amount-that_coin_value).
    So for all the coins, we return the smallest number as min(1+coinChange(amount-coin1_value),
    1+coinChange(amount-coin2_value, ......).
     */

    public int coinChange1(int[] coins, int amount) {
        if(amount==0)
            return 0;
        int n = Integer.MAX_VALUE;
        for(int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange1(coins, amount-coin);
                if(next >= 0)
                    curr = 1+next;
            }
            if(curr > 0)
                n = Math.min(n,curr);
        }
        return (n==Integer.MAX_VALUE) ? -1 : n;
    }

    /*
    Recursive + Memorization

    Then we observed that this algorithm may compute coinChange of same amount for many times,
    which are kind of duplicate,
    if we can store "amount->fewest_coin_count" into hashtble,
    then we don't need to recompute again.
     */
    private int[] amountArray;
    public int coinChange2(int[] coins, int amount) {
        amountArray = new int[amount+1];
        return solve(coins,amount);
    }

    public int solve(int[] coins, int amount) {
        if(amount==0)
            return 0;
        if(amountArray[amount]!=0)
            return amountArray[amount];
        int n = Integer.MAX_VALUE;
        for(int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = solve(coins, amount-coin);
                if(next >= 0)
                    curr = 1+next;
            }
            if(curr > 0)
                n = Math.min(n,curr);
        }
        int finalCount = (n==Integer.MAX_VALUE) ? -1 : n;
        amountArray[amount] = finalCount;
        return finalCount;
    }

    /*
    DP + Memorization
     */
    public int coinChange3(int[] coins, int amount) {
        // table[i] will be storing
        // the minimum number of coins
        // required for i value. So
        // table[V] will have result
        int table[] = new int[amount + 1];

        // Base case (If given value V is 0)
        table[0] = 0;

        // Initialize all table values as Infinite
        for (int i = 1; i <= amount; i++)
            table[i] = Integer.MAX_VALUE;

        // Compute minimum coins required for all
        // values from 1 to amount
        for (int i = 1; i <= amount; i++)
        {
            // Go through all coins smaller than i
            for (int j = 0; j < coins.length; j++)
                if (coins[j] <= i)
                {
                    int sub_res = table[i - coins[j]];
                    if (sub_res != -1
                            && sub_res + 1 < table[i])
                        table[i] = sub_res + 1;

                }

        }
        return (table[amount] == Integer.MAX_VALUE)? -1: table[amount];
    }



    @Test
    public void test(){
        System.out.println(coinChange1(new int[]{5,4,3},11));
    }

}
