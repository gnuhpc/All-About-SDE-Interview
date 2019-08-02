package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class CountRangeSum327 {
    //Method1 : Naive 方法 , LTE for sure
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

    public int countRangeSum2(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }

        // Build prefix array
        // Note that prefix array always size n + 1
        // prefix[i] - prefix[0] == sum of first i numbers
        // prefix[i] - prefix[j] == sum of (jth .. ith] numbers
        int n = nums.length;
        long[] preSums = new long[n + 1];
        preSums[0] = 0;
        for (int i = 0; i < n; ++i)
            preSums[i + 1] = preSums[i] + nums[i];

        return countWhileMergeSort(preSums, 0, n, lower, upper);
    }

    // l and r are index to the prefix array, NOT index to the original array
    // l and r cannot be the same
    private int countWhileMergeSort(long[] prefix, int l, int r, int lower, int upper) {
        if (l >= r) {
            return 0;
        }

        int mid = l + (r - l) / 2;

        // Recurse on left and right halves and count segments within each half that fall into the range
        int count = countWhileMergeSort(prefix, l, mid, lower, upper) +
                    countWhileMergeSort(prefix, mid + 1, r, lower, upper);

        // Array to store merged numbers
        long[] mergedArray = new long[r - l + 1];
        int mergedArrayIndex = 0;
        int rightToBeMergedIndex = mid + 1;

        // Left side index
        int i1 = l;

        // Right side lower bound index
        int a = mid + 1;

        // Right side upper bound index
        int b = mid + 1;

        // Walk through left side, and count number of elements from
        // the right side with which can form a range that fall into
        // the given target range.
        while (i1 <= mid) {
            while (a <= r && prefix[a] - prefix[i1] < lower) a++;
            while (b <= r && prefix[b] - prefix[i1] <= upper) b++;
            count += (b - a);

            // Try to insert all numbers from right side that are smaller than
            // prefix[i1] into merged array before insert prefix[i1].
            while (rightToBeMergedIndex <= r && prefix[rightToBeMergedIndex] < prefix[i1]) {
                mergedArray[mergedArrayIndex++] = prefix[rightToBeMergedIndex++];
            }

            mergedArray[mergedArrayIndex++] = prefix[i1++];
        }

        // Insert the rest of right hand side if any
        while (rightToBeMergedIndex <= r) {
            mergedArray[mergedArrayIndex++] = prefix[rightToBeMergedIndex++];
        }

        // Copy the shorted section back
        for (int i = l; i <= r; i++) {
            prefix[i] = mergedArray[i - l];
        }

        return count;
    }

    @Test
    public void test(){
        int arr[] = { -2,5,-1 };
        int L = -2;
        int R = 2;

        System.out.println(countRangeSum(arr, L, R));
    }
}
