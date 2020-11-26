package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class MaximumGap164 {

    // Method 1 counting sort based，内存不够用,OOM
    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int[] bucket = new int[max + 1];

        for (int n : nums) {
            bucket[n]++;
        }

        int[] temp = new int[nums.length];

        for (int i = 0, j = 0; i < bucket.length && j < nums.length; i++) {
            if (bucket[i] != 0) temp[j++] = i;
        }

        int maxGap = Integer.MIN_VALUE;

        for (int i = 1; i < temp.length; i++) {
            if (temp[i] - temp[i - 1] > maxGap) {
                maxGap = temp[i] - temp[i - 1];
            }
        }

        return maxGap;
    }

    // add by Tina
    public int maximumGap2(int[] num) {
        if (num == null || num.length < 2) return 0;
        // get the max and min value of the array
        int min = num[0], max = num[0];
        for (int val : num) {
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        // the minimum possibale gap, ceiling of the integer division
        int gap = (int) Math.ceil((double) (max - min) / (num.length - 1));
        if (gap == 0) return 0;
        int bucketLen = (max - min) / gap + 1; // != num.length - 1
        int[] bucketsMIN = new int[bucketLen]; // store the min value in that bucket
        int[] bucketsMAX = new int[bucketLen]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);

        // put numbers into buckets
        for (int val : num) {
            int idx = (val - min) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(val, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(val, bucketsMAX[idx]);
        }

        // scan the buckets for the max gap
        int maxGap = 0, prev = min;
        for (int i = 0; i < bucketLen; i++) {
            // empty bucket
            if (bucketsMAX[i] == Integer.MIN_VALUE) continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - prev);
            // update previous bucket value
            prev = bucketsMAX[i];
        }
        return maxGap;
    }

    @Test
    public void test() {
        maximumGap2(new int[]{1, 9, 2, 3, 4, 6, 8, 10});
    }
}
