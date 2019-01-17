package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

public class Rotate189 {
    public void rotate(int[] nums, int k) {
        if (k<=0 || nums == null || nums.length ==0) return;

        k %= nums.length ;
        reverseArray(nums,0,nums.length-k);
        reverseArray(nums,nums.length-k,nums.length);
        reverseArray(nums,0,nums.length);
    }

    private void reverseArray(int[] nums, int left, int right){
        if (nums == null || nums.length<=1 || left < 0 || right > nums.length){
            return;
        }

        for (int i = left,j = right -1; i < j ; i++,j--) {
            swapElements(nums,i,j);
        }
    }

    private void swapElements(int[]nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test(){
        int[] arr = new int[]{1, 2};
        // expect: [5,6,7,1,2,3,4]
        rotate(arr,3);
        Utils.printArray(arr);
    }
}
