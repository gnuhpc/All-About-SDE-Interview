package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 19-7-15
 */
public class Rob213 {


    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归 ,198进化而来
     */

    private int[] memo;
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];

        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int nContainFirst = tryRob(nums,1,nums.length);
        Arrays.fill(memo, -1);
        int yContainFirst = tryRob(nums,0,nums.length-1);

        return Math.max(nContainFirst,yContainFirst);
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
            max = Math.max(max, tryRob(nums,i+1,end));
        }
        memo[start] = max;
        return max;
    }

}
