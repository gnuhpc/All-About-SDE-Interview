package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class Rob198 {
    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归
     */

    private int[] memo;

    public int rob1(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0, nums.length);
    }

    //左开右闭
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
            max = Math.max(max, tryRob(nums,i+1,end));
        }
        memo[start] = max;
        return max;
    }


    private int[] res;
    /* TODO DFS+memorization -> DP 的典型案例
    DP ,这个完全可以通过上边的步骤改出来，-- 本质递推
     */

    public int rob2(int[] nums) {
        if (nums.length==0) { return 0;}
        if (nums.length==1) { return nums[0];}
        res = new int[nums.length];
        Arrays.fill(res,-1);

        res[0] = nums[0];
        res[1] = Math.max(nums[0],nums[1]);

        for (int idx = 2; idx < nums.length; idx++) {
            res[idx] = Math.max(
                    nums[idx] + res[idx - 2],
                    res[idx-1]
            );
        }

        return res[nums.length-1];
    }
}
