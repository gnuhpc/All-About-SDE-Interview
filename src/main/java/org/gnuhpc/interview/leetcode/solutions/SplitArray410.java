package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;
import org.testng.Assert;

public class SplitArray410 {
    private int ans;
    private int n, m;

    /**
     * @param nums         输入数组
     * @param i            当前的数字
     * @param cntSubarrays 目前的子数组个数
     * @param curSum       目前数组的和
     * @param curMax       目前各个子数组和的最大值
     */
    private void dfs(int[] nums, int i, int cntSubarrays, int curSum, int curMax) {
        // 当数字遍历结束，且子数组个数为m时，则更新结果为目前的结果和这个新结果的最小值.
        // 如果不为m则表示此次的分割无效，不更新结果
        if (i == n) {
            if (cntSubarrays == m) {
                ans = Math.min(ans, curMax);
            }

            return;
        }

        // 当i没有遍历结束时有两种选择：
        // 1. append it to the previous subarray
        if (i > 0) {
            dfs(nums, i + 1, cntSubarrays, curSum + nums[i], Math.max(curMax, curSum + nums[i]));
        }
        // 2. start a new subarray starting with that element
        if (cntSubarrays < m) {
            dfs(nums, i + 1, cntSubarrays + 1, nums[i], Math.max(curMax, nums[i]));
        }
    }

    public int splitArray(int[] nums, int M) {
        ans = Integer.MAX_VALUE;
        n = nums.length;
        m = M;
        dfs(nums, 0, 0, 0, 0);
        return ans;
    }


    /*
    Approach #2 Dynamic Programming [Accepted]
Intuition
The problem satisfies the non-aftereffect property. We can try to use dynamic programming to solve it.

The non-aftereffect property means, once the state of a certain stage is determined, it is not affected by the state in the future.
In this problem, if we get the largest subarray sum for splitting nums[0..i] into j parts, this value will not be affected by how we split the remaining part of nums.

To know more about non-aftereffect property, this link may be helpful :
http://www.programering.com/a/MDOzUzMwATM.html

Algorithm

Let's define f[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts.

Consider the jth subarray. We can split the array from a smaller index k to i to form it.
Thus f[i][j] can be derived from max(f[k][j - 1], nums[k + 1] + ... + nums[i]).
For all valid index k, f[i][j] should choose the minimum value of the above formula.

The final answer should be f[n][m], where n is the size of the array.

For corner situations, all the invalid f[i][j] should be assigned with INFINITY,
and f[0][0] should be initialized with 0.


     */
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        int[] sub = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }

/*  二分法
We can easily find a property for the answer:
If we can find a splitting method that ensures the maximum largest subarray sum will not exceed a value x,
then we can also find a splitting method that ensures the maximum largest subarray sum will not exceed any value y that is greater than x.

Lets define this property as F(x) for the value x. F(x) is true means we can find a splitting method that ensures the maximum largest subarray sum will not exceed x.
From the discussion above, we can find out that for x ranging from -INFINITY to INFINITY,
F(x) will start with false, then from a specific value x0, F(x) will turn to true and stay true forever.
Obviously, the specific value x0 is our answer.

Algorithm
We can use Binary search to find the value x0. Keeping a value mid = (left + right) /
If F(mid) is false, then we will search the range [mid + 1, right];
If F(mid) is true, then we will search [left, mid - 1].

For a given x, we can get the answer of F(x) using a greedy approach.
Using an accumulator sum to store the sum of the current processing subarray and a counter cnt to count the number of existing subarrays. We will process the elements in the array one by one. For each element num, if sum + num <= x, it means we can add num to the current subarray without exceeding the limit. Otherwise, we need to make a cut here, start a new subarray with the current element num.
This leads to an increment in the number of subarrays.
After we have finished the whole process, we need to compare the value cnt to the size limit of subarrays m.
If cnt <= m, it means we can find a splitting method that ensures the maximum largest subarray sum will not exceed x. Otherwise, F(x) should be false.
*/

    public int splitArray3(int[] nums, int m) {
        long L = 0, R = 1;
        for (int i = 0; i < nums.length; i++) {
            R += nums[i];//m>=1
        }
        long ans = 0;
        while (L < R) {
            //对于给定的m，寻找出对应的mid，满足mid最小
            long mid = (L + R) / 2;
            if (guess(nums, m, mid)) {
                R = mid;
                ans = mid;
            } else {
                L = mid + 1;
            }
        }
        return (int) ans;
    }

    //判断m分割，能否满足每个分割数组 最大值小于mid的情况  贪心处理
    private boolean guess(int[] nums, int m, long mid) {
        long sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            //如果超出mid，则该分割数组最大值超过，划分下一个数组
            if (sum + nums[i] > mid) {
                m--; //此时箱子数量少一个
                sum = nums[i];
                if (nums[i] > mid) {
                    return false;
                }
            } else {
                sum += nums[i];
            }
        }
        return m >= 1;
    }


    @Test
    public void test() {
        Assert.assertEquals(splitArray(new int[]{1, 4, 4}, 2), 18);
    }
}
