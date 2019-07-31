package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;

import java.util.Arrays;

// Data must be integers or strings
public class RadixSort {
    public static void main(String[] args) {

        int[] radixArray = { 4725, 4586, 1330, 8792, 1594, 5729 };
        radixSort(radixArray);
        Utils.printArray(radixArray);
    }

    public static void radixSort(int[] nums) {
        //simple linear search for the max item
        int max = Arrays.stream(nums).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(nums, exp);
    }

    public static void countSort(int[] nums, int exp) {

        int[] count = new int[10]; //注意这个时候就不用取range了，这个case中这个优化是极小的
        int[] output = new int[nums.length];

        // Store count of occurrences in count[]
        for (int num : nums) count[(num / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (int i = nums.length - 1; i >= 0; i--) {
            output[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, nums, 0, nums.length);
    }
}
