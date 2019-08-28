package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class NextPermutation31 {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--; // Find 1st id i that breaks descending order
        //i < 0，出现在都是降序的时候
        if(i >= 0) {                           // If not entirely descending
            int j = nums.length - 1;              // Start from the end
            while (nums[j] <= nums[i]) j--;           // Find rightmost first larger id j
            swap(nums, i, j);                     // Switch i and j
        }
        reverse(nums, i + 1, nums.length - 1);       // Reverse the descending sequence
        //Utils.printArray(nums);
    }


    private void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }

    @Test
    public void test(){
        nextPermutation(new int[]{1,4,3,2});
        nextPermutation(new int[]{1,2,3,4});
        nextPermutation(new int[]{1,3,2});
    }
}
