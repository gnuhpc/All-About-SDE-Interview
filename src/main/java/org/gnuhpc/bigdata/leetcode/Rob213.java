package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 19-7-15
 */
public class Rob213 {
    int[] res;

    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归 ,198进化而来
     */

    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        res = new int[nums.length];
        Arrays.fill(res, -1);

        int nContainFirst = solve(1,nums.length-1,nums);
        Arrays.fill(res, -1);
        int yContainFirst = solve(0,nums.length-2,nums);

        return Math.max(nContainFirst,yContainFirst);
        //自顶向下，一般从最后的开始, 从最后一家开始抢，规模缩小
    }

    private int solve(int end, int idx, int[] nums){
        if (idx < end) return 0;
        if (res[idx]!=-1) return res[idx];

        //包含两种决策
        int max =  Math.max(nums[idx] + solve(end,idx - 2,nums),solve(end,idx-1,nums));
        res[idx] = max;

        return max;
    }
}
