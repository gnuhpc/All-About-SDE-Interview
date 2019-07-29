package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class MaximumGap164 {
    //Method 1: using bucket sort like method
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

    // Method 2 标准bucket，内存不够用
    public int maximumGap2(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int max = nums[0];
        for(int i=1; i<nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        int[] bucket = new int[max+1];

        for (int n: nums){
            bucket[n]++;
        }

        int[] temp = new int[nums.length];

        for (int i = 0,j=0; i < bucket.length && j<nums.length; i++) {
            if (bucket[i]!=0) temp[j++] = i;
        }

        int maxGap = Integer.MIN_VALUE;

        for (int i = 1; i < temp.length; i++) {
            if (temp[i] - temp[i-1] > maxGap){
                maxGap = temp[i] - temp[i-1];
            }
        }

        return maxGap;
    }

    @Test
    public void test(){
        maximumGap2(new int[]{1,9,2,3,4,6,8,10});
    }
}
