package org.gnuhpc.bigdata.leetcode;


public class NumArray303 {
    //prefix sum
    private int[] preSum;
    public NumArray303(int[] nums) {

        preSum = new int[nums.length+1];

        preSum[0] = 0;
        for(int i = 1; i <= nums.length; i++){
            preSum[i] = nums[i-1] + preSum[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return preSum[j+1]- preSum[i]; //要注意开闭区间
    }
}
