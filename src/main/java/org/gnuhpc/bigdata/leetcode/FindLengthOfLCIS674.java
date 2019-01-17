package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class FindLengthOfLCIS674 {
    /*
    Method1 : 双指针，优点是如果有follow up则可以返回完整上升子序列
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int start = -1;
        int end = start + 1;
        int max = 1;

        while (end < nums.length - 1){
            if (nums[end+1]>= nums[end] ) end++;
            else{
                max = Math.max(max,end-start);
                start = end;
                end = start + 1;
            }
        }

        if (end == nums.length -1)
            max = Math.max(max,end-start);

        return max;
    }

    /*
    Method2 :  纯粹计数
     */

    public int findLengthOfLCIS2(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int res = 0,cnt = 0;
        for(int i = 0;i < nums.length;i++){
            //处理了nums长度为1的情况,i == 0 在前
            if(i == 0 || nums[i-1] < nums[i])
                res = Math.max(res,++cnt);
            else
                cnt = 1;
        }
        return res;
    }

    @Test
    public void test(){
        System.out.println(findLengthOfLCIS(new int[]{}));
    }
}
