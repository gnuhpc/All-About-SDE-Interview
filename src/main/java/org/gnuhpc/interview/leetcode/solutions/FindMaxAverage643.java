package org.gnuhpc.interview.leetcode.solutions;

public class FindMaxAverage643 {
    /*
    Method1 : 按照题意编写
     */
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0, j = k - 1; j < nums.length; i++, j++) {
            int sum = 0;
            for (int l = i; l < i + k; l++) {
                sum += nums[l];
            }

            max = Math.max(max, sum);
        }

        return ((double) max) / k;
    }

    /*
    Method2 : 滑动窗口
     */

    public double findMaxAverage2(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int currMaxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum + nums[i] - nums[i - k];
            currMaxSum = Math.max(sum, currMaxSum);
        }
        return currMaxSum * 1.0 / k;
    }

    public double findMaxAverage3(int[] nums, int k) {
        double max = 0;
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i == k - 1) {
                max = sum / k;
            } else if (i >= k) {
                sum -= nums[i - k];
                max = Math.max(max, sum / k);
            }
        }
        return max;
    }

    /*
    Method3: 前缀和
     */
    public double findMaxAverage4(int[] nums, int k) {
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }
        if (k >= nums.length) return 1.0 * presum[nums.length] / k;

        int max = Integer.MIN_VALUE;
        for (int j = 0; j + k <= nums.length; j++) {
            max = Math.max(max, presum[j + k] - presum[j]);
        }

        return max * 1.0 / k;
    }
}
