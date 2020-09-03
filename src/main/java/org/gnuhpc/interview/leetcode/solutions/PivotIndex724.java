package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/15
 */
public class PivotIndex724 {
    //暴力解
    public int pivotIndex(int[] nums) {
        if (nums == null) return -1;
        int n = nums.length;
        if (n == 1) return 0;

        int[] presum = new int[n + 1];


        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            if (presum[i - 1] == presum[n] - presum[i]) return i - 1;
        }


        return -1;
    }


    //减少重复计算
    /*
    S 是数组的和，当索引 i 是中心索引时，位于 i 左边数组元素的和 leftsum 满足 S - nums[i] - leftsum。
我们只需要判断当前索引 i 是否满足 leftsum==S-nums[i]-leftsum 并动态计算 leftsum 的值。
     */
    public int pivotIndex2(int[] nums) {
        int S = 0, leftsum = 0;
        for (int x : nums) S += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == S - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }
}
