package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class CountRangeSum327 {
    //Method1 : Naive 方法
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        //构造前缀和数组,the prefix sum array has size n + 1, where prefix[i] = prefix[i – 1] + nums[i – 1].
        //preSum(i)代表有前i个数字的和
        long[] preSums = new long[n + 1];
        preSums[0] = 0;
        for (int i = 0; i < n; ++i)
            preSums[i + 1] = preSums[i] + nums[i];

        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (preSums[j] - preSums[i] >= lower && preSums[j] - preSums[i] <= upper)
                    ans++;

        return ans;
    }

    @Test
    public void test(){
        int arr[] = { -2,5,-1 };
        int L = -2;
        int R = 2;

        System.out.println(countRangeSum(arr, L, R));
    }
}
