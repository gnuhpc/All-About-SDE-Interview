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
}
