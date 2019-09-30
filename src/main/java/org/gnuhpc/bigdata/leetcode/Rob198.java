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

    //add by tina
    /**
     * 1、没有思路，先想暴力解法。本质是从数组里面取x（x=[0,n-1]）个数组合问题，
     * 然后遍历组合，求最优解。O((2^n)*n)。通常求最优解，可以尝试找找递归+重叠字问题
     * 2、memo search的状态定义是考虑偷取[x,...,n-1]范围里的房子
     * 状态转移方程是 f(0) = max{v(0)+f(2),v(1)+f(3),v(2)+f(4),...,v(n-3)+f(n-1),v(n-2),v(n-1)}
     * 3、DP参考上面的状态方程
     * 4、考虑memo search另一套状态定义，考虑偷取[0,...,x]范围的房子
     * 状态转移方程是f(n-1) = max{v(n-1)+f(n-3),v(n-2)+f(n-4),...,v(2)+f(0),v(1),v(0)}
     */

    //private int[] memo;
    public int rob3(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //因为数组是非负整数，递归遍历中可能取单个元素的组合，故需要初始化为-1，而不是0
        //也可以定义为Integer[] memo，做判空处理
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return tryRob(nums,0);

    }
    //考虑偷取[x,...,n-1]范围里的房子
    public int tryRob(int[] nums,int index){
        if(index >= nums.length) return 0;
        if(memo[index] != -1) return memo[index];
        int mx = 0;
        for(int i = index;i<nums.length;i++){//注意i从index开始
            mx = Math.max(mx,nums[i] + tryRob(nums,i+2));
        }
        memo[index] = mx;
        return mx;
    }

    public int rob4(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //因为数组是非负整数，递归遍历中可能取单个元素的组合，故需要初始化为-1，而不是0
        // DP双重循环,考虑偷取[x,...,n-1]范围里的房子
        // 初始状态DP[n-1] = nums[n-1];DP[n-2] = max(nums[n-2],DP[n-1])
        // DP[i] = max{nums[i]+dp[i+2],nums[i+1]+dp[i+3],...,nums[n-1]}
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        for(int i = n-2; i>=0;i--)
            for(int j = i;j<n;j++){
                //因为要用dp[j+2]，需对j+2做合法判断
                dp[i] = Math.max(dp[i],nums[j]+(j+2<n ? dp[j+2]:0));
            }
        return dp[0];
    }

    // memo search
    // f(n-1) = max{v(n-1)+f(n-3),v(n-2)+f(n-4),...,v(2)+f(0),v(1),v(0)}
    public int rob5(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //因为数组是非负整数，递归遍历中可能取单个元素的组合，故需要初始化为-1，而不是0
        //也可以定义为Integer[] memo，做判空处理
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return tryRob(nums,nums.length-1);

    }

    //考虑偷取[0,...,x]范围里的房子 倒着来
    public int tryRob5(int[] nums,int index){
        if(index <0) return 0;
        if(memo[index] != -1) return memo[index];
        int mx = 0;
        for(int i = index;i>=0;i++){//注意i从index开始
            mx = Math.max(mx,nums[index] + tryRob(nums,i-2));
        }
        memo[index] = mx;
        return mx;
    }

    // DP[i] = max{nums[i]+DP[i-2],nums[i-1]+DP[i-3],...,nums[2]+DP[0],nums[1],nums[0])
    public int rob6(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //因为数组是非负整数，递归遍历中可能取单个元素的组合，故需要初始化为-1，而不是0
        // DP双重循环,考虑偷取[x,...,n-1]范围里的房子
        // 初始状态DP[0] = nums[0];DP[1] = max(nums[1],DP[0])

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 0; i<n;i++)
            for(int j = i;j>=0;j--){
                //因为要用dp[j+2]，需对j+2做合法判断
                dp[i] = Math.max(dp[i],nums[j]+(j-2>=0 ? dp[j-2]:0));
            }
        return dp[n-1];
    }

    /*
    Method7 : 从后往前 TODO
     */
    public int rob7(int[] nums) {
        if (nums.length == 1) return nums[0];
        res = new int[nums.length];
        Arrays.fill(res, -1);
        //自顶向下，一般从最后的开始, 从最后一家开始抢，规模缩小
        return solve(0, nums.length - 1, nums);
    }


    private int solve(int end, int idx, int[] nums) {
        if (idx < end) return 0;
        if (res[idx] != -1) return res[idx];

        //包含两种决策
        int max = Math.max(nums[idx] + solve(end, idx - 2, nums), solve(end, idx - 1, nums));
        res[idx] = max;

        return max;
    }

}
