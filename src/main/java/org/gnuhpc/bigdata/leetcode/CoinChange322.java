package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CoinChange322 {

    /*
    Method1 : Recursive + Memorization
     */

    public int coinChange(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }


    /*
    DP + Memorization
     */
    public int coinChange1(int[] coins, int amount) {
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
