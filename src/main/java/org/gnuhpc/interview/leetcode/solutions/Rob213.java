package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 19-7-15
 */
public class Rob213 {


    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归 ,198进化而来
    House Robber I的升级版. 因为第一个element 和最后一个element不能同时出现. 则分两次call House Robber
    I. case 1: 不包括最后一个element. case 2: 不包括第一个element.
     */
    private int[] memo;
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        memo = new int[n];

        Arrays.fill(memo, -1);
        int n1 = dfs(nums, 1, n - 1);

        Arrays.fill(memo, -1);
        int n2 = dfs(nums,0, n - 2);
        return Math.max(n1,n2);
    }

    private int dfs(int[] nums,int i,int j){
        if( i  > nums.length - 1) return 0;
        if(memo[i] != -1) return memo[i];
        int maxMoney = 0;
        for (int k = i; k <= j; k++) {
            maxMoney = Math.max(maxMoney,dfs(nums,k + 2,j) + nums[k]);
        }
        memo[i] = maxMoney;
        return memo[i];
    }


    // add by tina,DP
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        return Math.max(dp(nums, 0, nums.length - 2), dp(nums, 1, nums.length - 1));
    }

    public int dp(int[] nums, int index, int end) {
        int n = end - index + 1;
        int[] dp = new int[n];
        dp[0] = nums[index];
        dp[1] = Math.max(nums[index], nums[index + 1]);
        for (int i = 2; i < n; i++) {//dp的偏移量从0开始，nums从index开始
            dp[i] = Math.max(dp[i - 1], nums[index + i] + dp[i - 2]);
        }
        return dp[n - 1];
    }
}
