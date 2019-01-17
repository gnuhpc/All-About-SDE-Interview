package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

//http://www.puzzlr.org/understanding-kadanes-algorithm-maximum-subarray-problem/
public class MaxSubArray53 {
    public int maxSubArray(int[] nums) {
        int localMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //Each number “decides” if it wants to use the previous subarray sum
            // or if it wants to start a new subarray from scratch.
            // 所以，这个if还有另一个写法 localMax = Math.max(num[i], localMax + num[i])
            if (localMax < 0) { //既然小于0了，那前边的都是欠债，扔了算求
                localMax = nums[i];
            } else {
                localMax = localMax + nums[i]; //这个是关键，因为是连续的，因此
            }
            if (localMax > globalMax) globalMax = localMax;
        }

        return globalMax;
    }

    //采用滑动窗口解决。sum 如果小于0，置为0，再加上当前值。
    // 然后再与max相比，取大的。
    public int maxSubArraySlidingWindow(int[] nums) {
        // -1 is not proper for illegal input
        if (nums == null || nums.length ==0) return -1;

        int sum = 0, maxSub = Integer.MIN_VALUE;
        for (int num : nums) {
            // drop negtive sum
            sum = Math.max(sum, 0);
            sum += num;
            // update maxSub
            maxSub = Math.max(maxSub, sum);
        }

        return maxSub;

    }

    //记忆化搜索 + DP
    public int maxSubArrayMemo(int[] nums) {
        // -1 is not proper for illegal input
        if (nums == null || nums.length == 0) return -1;

        int size = nums.length;
        int[] local = new int[size];
        int[] global = new int[size];
        local[0] = nums[0];
        global[0] = nums[0];
        for (int i = 1; i < size; i++) {
            // drop local[i - 1] < 0
            local[i] = Math.max(nums[i], local[i - 1] + nums[i]);
            // update global with local
            global[i] = Math.max(global[i - 1], local[i]);
        }

        return global[size - 1];
    }

    @Test
    public void test() {
        int[] arr = {-1, 4, 2, -1};

        System.out.println(maxSubArray(arr));
    }
}
