package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLIS300 {
    /*
    Method1: 暴力搜索, LTE
     */

    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    /*
    we consider two cases:

The current element is larger than the previous element included in the LIS.
In this case, we can include the current element in the LIS.
 Thus, we find out the length of the LIS obtained by including it.
 Further, we also find out the length of LIS possible by not including the current element in the LIS.
 The value returned by the current function call is,
 thus, the maximum out of the two lengths.

The current element is smaller than the previous element included in the LIS.
In this case, we can't include the current element in the LIS.
Thus, we find out only the length of the LIS possible by not including the current element in the LIS,
 which is returned by the current function call.
     */
    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0, nottaken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    /*
    Method2 : memo search ,TODO: 二维记忆搜索数组舒初始化方法
     */

    public int lengthOfLIS2(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        int result = Math.max(taken, nottaken);
        memo[previndex + 1][curpos] =  result;
        return result;
    }


    /*
    Method3: DP
     */
    //https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
    // 如果要求这个子序列，则参考：
    // https://www.hackerrank.com/challenges/longest-increasing-subsequent/problem
    public int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        if (n==0){ return 0;}
        int lis[] = new int[n];
        int i,j;

        /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++ )
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( nums[i] > nums[j] )
                    lis[i] = Math.max(lis[i], lis[j] + 1);

        /* Pick maximum of all LIS values */

        return Arrays.stream(lis).max().getAsInt();
    }



    @Test
    public void test(){
        System.out.println(lengthOfLIS3(new int[]{10,9,2,5,3,7,101,18}));
    }
}
