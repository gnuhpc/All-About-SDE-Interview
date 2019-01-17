package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class MaximumGap164 {
    //Brute force, O(nlogn)
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length<2) return 0;

        Arrays.sort(nums);

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] > max){
                max = nums[i] - nums[i-1];
            }
        }

        return max;
    }


    //Method 2: using bucket sort like method
    class Bucket{
        int low;
        int high;
        public Bucket(){
            low = -1;
            high = -1;
        }
    }

    public int maximumGap2(int[] num) {
        if(num == null || num.length < 2){
            return 0;
        }

        int max = num[0];
        int min = num[0];
        for(int i=1; i<num.length; i++){
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }

        // initialize an array of buckets
        //映射桶的第一步：创建一个length+1的数组
        Bucket[] buckets = new Bucket[num.length+1]; //project to (0 - n)
        for(int i=0; i<buckets.length; i++){
            buckets[i] = new Bucket();
        }

        //映射桶的第二步，获取interval
        double interval = interval(num.length,max,min);
        //distribute every number to a bucket array
        for(int i=0; i<num.length; i++){
            //映射桶的第三步，获取映射的index
            int index = index(num[i], min, interval);

            if(buckets[index].low == -1){
                buckets[index].low = num[i];
                buckets[index].high = num[i];
            }else{
                buckets[index].low = Math.min(buckets[index].low, num[i]);
                buckets[index].high = Math.max(buckets[index].high, num[i]);
            }
        }

        //scan buckets to find maximum gap, 因为落在同一个桶内的数字大小差最大为ceil(interval)
        int result = 0;
        int prev = buckets[0].high;
        for(int i=1; i<buckets.length; i++){
            if(buckets[i].low != -1){
                result = Math.max(result, buckets[i].low-prev);
                prev = buckets[i].high;
            }

        }

        return result;
    }

    private double interval(int length, int max, int min){
        return (double) length / (max - min); //一格距离代表的原数组长度
    }

    private int index(int num, int min, double interval){
        return (int) ((num - min) * interval);
    }

    // Method 3 标准bucket，可能会内存不够用
    public int maximumGap3(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int max = nums[0];
        for(int i=1; i<nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        int[] bucket = new int[max+1];

        for (int n: nums){
            bucket[n]++;
        }

        int[] temp = new int[nums.length];

        for (int i = 0,j=0; i < bucket.length && j<nums.length; i++) {
            if (bucket[i]!=0) temp[j++] = i;
        }

        int maxGap = Integer.MIN_VALUE;

        for (int i = 1; i < temp.length; i++) {
            if (temp[i] - temp[i-1] > maxGap){
                maxGap = temp[i] - temp[i-1];
            }
        }

        return maxGap;
    }

    @Test
    public void test(){
        maximumGap2(new int[]{1,9,2,3,4,6,8,10});
    }
}
