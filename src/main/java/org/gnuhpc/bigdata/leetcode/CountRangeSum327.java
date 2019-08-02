package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class CountRangeSum327 {
    //Method1 : Naive 方法
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        //构造前缀和数组,the prefix sum array has size n + 1, where prefix[i] = prefix[i – 1] + nums[i – 1].
        //preSum(i)代表有前i个数字的和
        long[] preSums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            preSums[i + 1] = preSums[i] + nums[i];

        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (preSums[j] - preSums[i] >= lower && preSums[j] - preSums[i] <= upper)
                    ans++;

        return ans;
    }

    //Method2 : Merge Sort counting
    //讲解：https://medium.com/@bill800227/leetcode-327-count-of-range-sum-e4e8724f1ff4
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] preSums = new long[n + 1];
        preSums[0] = 0;
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }

        return countWhileMergeSort(preSums, 0, n + 1, lower, upper);
    }

    // start and end are index to the preSum, NOT index to the original array
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = (start + end) / 2;
        // Recurse on left and right halves and count segments within each half that fall into the range
        int count = countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);

        int j = mid;
        int k = mid;
        int t = mid;

        long[] cache = new long[end - start];

        for (int i = start, r = 0; i < mid; i++, r++) {
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }

            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }

            while (t < end && sums[t] < sums[i]) {
                cache[r++] = sums[t++];
            }

            cache[r] = sums[i];
            count += j - k;
        }

        System.arraycopy(cache, 0, sums, start, t - start);

        return count;
    }

    //Method3: 二分
    public int countRangeSum3(int[] nums, int lower, int upper) {
        int len = nums.length;
        if (lower > upper || len <= 0) {
            return 0;
        }
        long[] preSums = new long[len+1];
        preSums[0] = 0;
        for (int i = 0; i < len; i++) {
            preSums[i+1] = preSums[i] + nums[i];
        }
        return getCount(nums, 0, len-1, preSums, lower, upper);
    }

    private int getCount(int[] nums, int left, int right, long[] preSums, int lower, int upper) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            if (nums[left] <= upper && nums[left] >= lower) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = (left + right) / 2;
        int count = 0;
        for (int i = left; i <= mid; i++) {
            for (int j = mid + 1; j <= right; j++) {
                //preSums[i] = First i nums' sum
                //preSums[j] = First j nums' sum
                //preSums[j] - preSums[i] = [i+1,j] nums' sum
                //so we need to add nums[i] back
                long tmp = preSums[j+1] - preSums[i+1] + nums[i];
                if ( tmp <= upper && tmp >= lower) {
                    count++;
                }
            }
        }
        return count + getCount(nums, left, mid, preSums, lower, upper) + getCount(nums, mid + 1, right, preSums, lower, upper);
    }


    @Test
    public void test(){
        int arr[] = { -1,1 };
        int L = 0;
        int R = 0;

        System.out.println(countRangeSum3(arr, L, R));
    }
}
