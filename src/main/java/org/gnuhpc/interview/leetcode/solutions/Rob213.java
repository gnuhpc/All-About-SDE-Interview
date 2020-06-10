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
        if (nums.length == 1) return nums[0];

        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int nContainFirst = tryRob(nums, 1, nums.length);
        //add by tina,注意重新赋值，因为第2轮最大值搜索对同一个索引计算结果不同
        Arrays.fill(memo, -1);
        int yContainFirst = tryRob(nums, 0, nums.length - 1);

        return Math.max(nContainFirst, yContainFirst);
        //自顶向下，一般从最后的开始, 从最后一家开始抢，规模缩小
    }

    private int tryRob(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        // 记忆化搜索可以避免重叠子问题的重复运算
        if (memo[start] != -1) {
            return memo[start];
        }
        // 下面是对状态转移方程的描述
        int max = 0;
        for (int i = start; i < end; i++) {
            max = Math.max(max, nums[i] + tryRob(nums, i + 2, end));
            max = Math.max(max, tryRob(nums, i + 1, end));
        }
        memo[start] = max;
        return max;
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
