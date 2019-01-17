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
}
