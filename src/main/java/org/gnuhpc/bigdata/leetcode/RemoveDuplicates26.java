package org.gnuhpc.bigdata.leetcode;

public class RemoveDuplicates26 {
    public static int removeDuplicates(int[] nums) {
        if (nums.length<=1) return nums.length;
        int l=0;

        for (int r = 1; r < nums.length; r++) {
            if(nums[r]>nums[l]){
                nums[++l] = nums[r];
            }
        }

        return l+1;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};

        int size = removeDuplicates(nums);

        System.out.println("The result size is: " + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
    }
}
