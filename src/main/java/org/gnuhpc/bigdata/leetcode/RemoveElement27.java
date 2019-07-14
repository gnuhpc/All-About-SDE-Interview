package org.gnuhpc.bigdata.leetcode;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class RemoveElement27 {
    public static int removeElement(int[] nums, int val) {
        int k=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val){
                if(i!=k){
                    swap(nums,i,k);
                }

                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int size = removeElement(nums,2);
        System.out.println("Size is :" + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }

    }

    // add by Tina
    // 思路：从后往前，找到=val的数，与len-1交换，交换后，len--
    public int removeElement2(int[] nums, int val) {
        if(nums == null || nums.length == 0 ) return 0;
        int len = nums.length;

        for(int i = len-1; i >= 0; i--){
            if(nums[i] == val){
                if(i != len-1) swap2(nums,i,len-1);
                len--;
            }
        }
        return len;
    }

    public void swap2(int[] nums,int i ,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
