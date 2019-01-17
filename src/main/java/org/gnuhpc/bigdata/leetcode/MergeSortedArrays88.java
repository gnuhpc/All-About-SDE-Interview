package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class MergeSortedArrays88 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};

        sortArrays(nums1,nums1.length-nums2.length, nums2, nums2.length);

        Arrays.stream(nums1).forEach(System.out::print);
    }

    private static void sortArrays(int[] nums1, int m, int[] nums2, int n) {
        int k = m+n-1;
        int i = m-1;
        int j = n-1;
        for(; i >= 0 && j >= 0; k--) {
            if(nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i--];
            }else{
                nums1[k] = nums2[j--];
            }
        }
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
