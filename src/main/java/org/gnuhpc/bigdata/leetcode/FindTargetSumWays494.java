package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 19-7-17
 */
public class FindTargetSumWays494 {
    //DFS + memorization
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return helper(nums, 0, 0, S, new HashMap<>());
    }

    private int helper(int[] nums, int index, int sum,
                       int S, Map<String, Integer> memo) {
        // 避免数字是重复，无法找到截断点
        String encodeString = index + "->" + sum;
        if (memo.containsKey(encodeString)) {
            return memo.get(encodeString);
        }
        if (index == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        }
        int curNum = nums[index];
        int add = helper(nums, index + 1, sum - curNum, S, memo);
        int minus = helper(nums, index + 1, sum + curNum, S, memo);
        memo.put(encodeString, add + minus);
        return add + minus;
    }

    /**  Method 2: DP
     * 494
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * sum(P) 前面符号为+的集合；sum(N) 前面符号为减号的集合
     * 所以题目可以转化为
     * sum(P) - sum(N) = target
     * => sum(nums) + sum(P) - sum(N) = target + sum(nums)
     * => 2 * sum(P) = target + sum(nums)
     * => sum(P) = (target + sum(nums)) / 2
     * 因此题目转化为01背包，也就是能组合成容量为sum(P)的方式有多少种
     * 建立dp，dp[i] = j代表数组nums中和为i的序列个数为j，初始dp[0] = 1
     */
    public static int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int p = (sum + S) / 2;
        int[] dp = new int[p + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = p; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[p];
    }

}
