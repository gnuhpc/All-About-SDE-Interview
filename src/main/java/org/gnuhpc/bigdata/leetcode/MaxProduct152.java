package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class MaxProduct152 {
    public int maxProduct(int[] nums) {
        if(nums == null && nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int n = nums.length;
        return findMax(nums,0,n);
    }

    private int findMax(int[] nums, int start, int end) {
        //递归退出条件
        if (start> nums.length-1) return Integer.MIN_VALUE;
        if (start +1 >= end){
            return nums[start];
        }

        for (int i = start; i < end; i++) {
            if (nums[i] ==0){
                return Math.max(Math.max(
                    findMax(nums,start,i),
                    findMax(nums,i+1,end)),0); //跳过0，左闭右开
            }
        }

        int numOfNeg = 0;
        for (int i = start; i < end; i++) {
            if (nums[i]<0){
                numOfNeg++;
            }
        }
/*
当一个数组中没有0存在，则分为两种情况：
1.负数为偶数个，则整个数组的各个值相乘为最大值；
2.负数为奇数个，则从左边开始，乘到最后一个负数停止有一个“最大值”，从右边也有一个“最大值”，
比较，得出最大值。
 */
        if (numOfNeg%2==0){
            int res = 1;
            for (int i = start; i < end; i++) {
                res *= nums[i];
            }

            return res;
        } else{
            int n = 0; //记录遍历中遇到的负数个数
            int res1 = 1;
            int res2 = 1;

            for (int i = start; i < end; i++) {
                if (nums[i]<0) n++;
                if (n<numOfNeg){
                    res1 *=nums[i];
                } else {
                    break;
                }
            }

            n=0;
            for (int i = end-1; i >=0 && n<numOfNeg; i--) {
                if (nums[i]<0) n++;
                if (n<numOfNeg){
                    res2 *=nums[i];
                } else {
                    break;
                }
            }

            return Math.max(res1,res2);
        }
    }

    @Test
    public void test(){
        //System.out.println(maxProduct(new int[]{2,3,-2,4}));
        //System.out.println(maxProduct(new int[]{0,1,0}));
        //System.out.println(maxProduct(new int[]{-1,-2,-3,0}));
        System.out.println(maxProduct(new int[]{0,2}));
    }

    // add by tina, DP
    public int maxProduct2(int[] nums) {
        assert nums.length > 0;
        int max = nums[0], min = nums[0], maxAns = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = max, mn = min;
            max = Math.max(Math.max(nums[i], mx * nums[i]), mn * nums[i]);
            min = Math.min(Math.min(nums[i], mx * nums[i]), mn * nums[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }

    // add by tina, 暴力解法
    public int maxProduct3(int[] nums) {
        int N = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; ++i) {
            int cur = 1;
            for (int j = i; j < N; ++j) {
                if (j == i)
                    cur = nums[i];
                else
                    cur = cur * nums[j];
                res = Math.max(res, cur);
            }
        }
        return res;
    }


}
