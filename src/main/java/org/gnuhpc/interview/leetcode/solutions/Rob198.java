package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class Rob198 {
    /**
     * 1、没有思路，先想暴力解法。本质是从数组里面取x（x=[0,n-1]）个数组合问题，
     * 然后遍历组合，求最优解。O((2^n)*n)。通常求最优解，可以尝试找找递归+重叠字问题
     * 2、memo search的状态定义是考虑偷取[x,...,n-1]范围里的房子
     * 状态转移方程是 f(0) = max{v(0)+f(2),v(1)+f(3),v(2)+f(4),...,v(n-3)+f(n-1),v(n-2),v(n-1)}
     * 3、DP参考上面的状态方程
     * 4、考虑memo search另一套状态定义，考虑偷取[0,...,x]范围的房子
     * 状态转移方程是f(n-1) = max{v(n-1)+f(n-3),v(n-2)+f(n-4),...,v(2)+f(0),v(1),v(0)}
     */

    /*
    dfs + Memoriaztion ，要先想出来DFS，然后加上记忆化搜索 -- 本质递归
     */

    private int[] memo;

    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //因为数组是非负整数，递归遍历中可能取单个元素的组合，故需要初始化为-1，而不是0
        //也可以定义为Integer[] memo，做判空处理
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);

    }

    //考虑偷取[x,...,n-1]范围里的房子
    public int tryRob(int[] nums, int index) {
        if (index >= nums.length) return 0;
        if (memo[index] != -1) return memo[index];
        int mx = 0;
        for (int i = index; i < nums.length; i++) {//注意i从index开始
            //当前的这个抢
            mx = Math.max(mx, nums[i] + tryRob(nums, i + 2));
            //当前的这个不抢
            mx = Math.max(mx, tryRob(nums, i + 1));
        }
        memo[index] = mx;
        return mx;
    }

    /* DFS+memorization -> DP 的典型案例
    DP ,这个完全可以通过上边的步骤改出来，-- 本质递推
     */

    private int[] dp;

    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        dp = new int[nums.length];
        Arrays.fill(dp, -1);

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int idx = 2; idx < nums.length; idx++) {
            dp[idx] = Math.max(
                    nums[idx] + dp[idx - 2],
                    dp[idx - 1]
            );
        }

        return dp[nums.length - 1];
    }
}
