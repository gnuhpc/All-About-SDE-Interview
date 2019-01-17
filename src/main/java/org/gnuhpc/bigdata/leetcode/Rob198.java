package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class Rob198 {
    int[] res;

    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归
     */

    public int rob(int[] nums) {
        res = new int[nums.length];
        Arrays.fill(res, -1);
        //自顶向下，一般从最后的开始, 从最后一家开始抢，规模缩小
        return solve(nums.length - 1,nums);
    }

    private int solve(int idx, int[] nums){
        if (idx < 0) return 0;
        if (res[idx]!=-1) return res[idx];

        //包含两种决策
        int max =  Math.max(nums[idx] + solve(idx - 2,nums),solve(idx-1,nums));
        res[idx] = max;

        return max;
    }


    /*
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
