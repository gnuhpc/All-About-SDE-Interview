package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class MaximumGap164 {
    //Method 1: using bucket sort like method
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], max, min, len);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                //找出下一个bucket中最小的那个和上一个最大的那个的插值，这个一定是既相邻，差值又是最大的一个
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private int bucket(int num, int max, int min, int length) { //注意这个归一化有三个要点：
        //1. 先进行除法进行归一
        //2. 转换为double
        //3. 乘以length
        return (int) (((double) (num - min) / (max - min)) * length);
    }

    // Method 2 counting sort based，内存不够用,OOM
    public int maximumGap2(int[] nums) {
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
    public int maximumGap3(int[] num) {
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
