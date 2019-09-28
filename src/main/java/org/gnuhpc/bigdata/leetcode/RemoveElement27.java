package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;

import java.util.Arrays;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class RemoveElement27 {
    //Method1: 不破坏数组的方法, k永远指向第一个val所在的位置, 一步步浮上来
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

    //Method2 : 破坏数组的方法
    public static int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,1,3,0,4,2};
        int size = removeElement(nums,2);
        System.out.println("Size is :" + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }

    }

    // add by Tina
    // 思路：从后往前，找到=val的数，与len-1交换，交换后，len--
    public int removeElement3(int[] nums, int val) {
        if(nums == null || nums.length == 0 ) return 0;
        int len = nums.length;

        for(int i = len-1; i >= 0; i--){
            if(nums[i] == val){
                if(i != len-1) Utils.swap(nums,i,len-1);
                len--;
            }
        }
        return len;
    }
}
