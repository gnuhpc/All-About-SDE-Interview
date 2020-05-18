package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/15
 */
public class PivotIndex724 {
    //暴力解
    public int pivotIndex(int[] nums) {
        if (nums == null) return -1;
        if (nums.length == 1) return 0;

        for (int i = 0; i < nums.length; i++) {
            int sum1 = 0, sum2 = 0;
            for (int j = 0; j < i; j++) {
                sum1 += nums[j];
            }

            for (int k = i + 1; k < nums.length; k++) {
                sum2 += nums[k];
            }

            if (sum1 == sum2) {
                return i;
            }
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
