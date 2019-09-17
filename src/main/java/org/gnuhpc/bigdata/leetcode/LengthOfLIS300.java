package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLIS300 {
    /*
    暴力搜索1
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
    Method2: DP
     */
    //https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
    // 如果要求这个子序列，则参考：
    // https://www.hackerrank.com/challenges/longest-increasing-subsequent/problem
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        // dp 数组全都初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    /*
    Method3: 二分法
    https://mp.weixin.qq.com/s?__biz=MzU2MDY5NTE4NA==&mid=2247484036&idx=1&sn=0c64b2fe828e76402cdd471ebd3b9834&chksm=fc05507bcb72d96d458a272437703410387e4f774f9541ba74bdcd12932af88713577eb3dac0&mpshare=1&scene=1&srcid=0917gmCIi8IKZYZkQ0AwHuyy&sharer_sharetime=1568712419437&sharer_shareid=c6956170fd4fb4420832b556d7c1fdc5#rd
     */
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // minLast[-1] is alwyas Integer.MAX_VALUE
        int[] minLast = new int[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }

        return binarySearch(minLast, Integer.MAX_VALUE);
    }

    // find the first number >= num
    private int binarySearch(int[] minLast, int num) {
        int start = 0, end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (minLast[mid] < num) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (minLast[start] >= num) {
            return start;
        }
        return end;
    }


    @Test
    public void test(){
        System.out.println(lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }
}
