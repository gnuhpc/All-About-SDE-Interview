package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

//首先明确一个不争的事实，数组是一个特殊的数组，里面的数都在1--length-1范围内，那么一旦某个数字有重复了，就会导致从中间劈开，
// 有重复数字的那一边数字多，假设[1,1,3,4,5,2],从中间劈开的中位数是3，而小于等于3的数字有4个，大于中位数3，
// 说明重复数字在左半边，high就被替换为mid
public class FindDuplicate287 {
    public static int findDuplicate(int[] nums) {
        if (nums.length == 0)
            return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2; //根据数组长度，寻找中位数
            //然后计算比中位数小的数字个数
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            //如果这个数字个数本身就比中位数大，那么
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }


    @Test
    public void test(){
        System.out.println(findDuplicate(new int[]{3,1,3,4,2}));
    }
}
