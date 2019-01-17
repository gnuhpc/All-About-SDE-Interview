package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class MoveZeros283 {
    //O(n) Time, O(n) Space
    public static void moveZeros(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int[] nonZeronums = new int[nums.length];
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeronums[j++] = nums[i];
            }
        }

        for (int i = 0; i < nonZeronums.length; i++) {
            nums[i] = nonZeronums[i];
        }

        for (int i = nonZeronums.length; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    //O(n) Time, O(1) Space
    public static void moveZerosInPlace(int[] nums) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        while (k < nums.length) {
            nums[k++] = 0;
        }
    }

    public static void moveZeroSwap(int[] nums){
        int k=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0){
                if(i!=k){
                    swap(nums,i,k);
                }

                k++;
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{0, 1, 3, 0, 5, 0, 0, 1};
        int[] nums2 = new int[]{0, 1, 3, 0, 5, 0, 0, 1};
        int[] nums3 = new int[]{0, 1, 3, 0, 5, 0, 0, 1};

        moveZeros(nums);
        moveZerosInPlace(nums2);
        moveZeroSwap(nums3);
        Arrays.stream(nums3).forEach(System.out::println);
        System.out.println(
            Arrays.stream(nums)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(""))
                    .equals(
            Arrays.stream(nums2)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(""))
            )
        );
    }
}
