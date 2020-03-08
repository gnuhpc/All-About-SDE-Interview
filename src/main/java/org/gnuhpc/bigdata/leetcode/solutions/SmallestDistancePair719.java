package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SmallestDistancePair719 {
    //二分法
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, low = 0, hi = nums[n - 1] - nums[0];
        while (low < hi) {
            //定义一个变量 mid 用来代表 distance。
            // 再定义一个变量 cnt 用来记录 distance 小于 mid 的组合的数量。
            int cnt = 0, j = 0, mid = (low + hi) / 2;
            // 如何求的 cnt 的值， nums 已经是一个有序的数组，
            // 从 i 位置出发，用 j >= i，不断查找满足条件的数，并且记录下来。
            for (int i = 0; i < n; ++i) {
                while (j < n && nums[j] - nums[i] <= mid) ++j;
                cnt += j - i - 1;
            }
            // 应用二分法之后，如果满足条件的组合个数 cnt 小于k，
            // 那么 mid 所代表的 distance 太小，下界 low = mid + 1
            // 否则 上界 high = mid
            if (cnt < k)
                low = mid + 1;

            else hi = mid;
        }

        return low;
    }

    // Bucket Sort
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int[] arr = new int[nums[nums.length - 1] + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                arr[Math.abs(nums[i] - nums[j])]++;
            }
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += arr[i];
            if (count >= k) {
                return i;
            }
        }
        return 0;
    }


    @Test
    public void test() {
        System.out.println(smallestDistancePair(new int[]{1, 3, 6, 9, 12, 15}, 3));
    }

}
