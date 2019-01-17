package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null) return -1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] <= nums.length && nums[i] > 0 && nums[nums[i] - 1] != nums[i]){
                Utils.swap(nums, nums[i]-1,i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i +1){
                return i+1;
            }
        }
        return nums.length + 1;
    }

    @Test
    public void test(){
        System.out.println(firstMissingPositive(new int[]{7,8,9,11,12}));
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
    }

}
