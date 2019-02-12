package org.gnuhpc.bigdata.leetcode;

public class MinimumSizeSubarraySum209 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,3};
        int s = 7;
        System.out.println(minSubArrayLen(s,nums));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE, sum = 0;

        int l=0, r=-1;

        while(r<nums.length){
            if(sum>=s){
                res = Math.min(res,r-l+1);
                sum-=nums[l];
                l++;
            } else {
                if (r==nums.length-1){
                    break;
                }
                r++;
                sum+=nums[r];
            }
        }

        if (res == Integer.MAX_VALUE){
            res = 0;
        }

        return res;
    }

    /**
     * 暴力解法，O(n^2),简单直观
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int s, int[] nums) {
        int min=Integer.MAX_VALUE, k=0; // min最小窗口大小，k当前最小窗口起始元素下标
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                if(sum >= s) {
                    int cur = j-i+1;
                    if(cur < min) {
                        k = i;
                        min = cur;
                    }
                    continue;
                }
            }
        }

        if(min == Integer.MAX_VALUE) return 0;
        for(int i=0;i<min;i++){
            nums[i] = nums[k+i];
        }

        return min;
    }
}
