package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class MinSubArrayLen209 {

    /**
     * 暴力解法，O(n^2) by 汪婷， why k?? 根本没有需要算k，最后一个for循环更没必要算
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE, k = 0; // min最小窗口大小，k当前最小窗口起始元素下标
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    int cur = j - i + 1;
                    if (cur < min) {
                        k = i;
                        min = cur;
                    }
                    continue;
                }
            }
        }

        if (min == Integer.MAX_VALUE) return 0;
        for (int i = 0; i < min; i++) {
            nums[i] = nums[k + i];
        }

        return min;
    }

    //Method2: preSum方法，因为是非负数，因此累加都是递增的
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) {
            if (nums[0] >= s) return 1;
            else {
                return 0;
            }
        }

        int n = nums.length;

        int[] preSums = new int[n + 1];
        preSums[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSums[i] = preSums[i - 1] + nums[i - 1];
        }

        if (preSums[n] < s) return 0;

        int res = Integer.MAX_VALUE;
        for (int i = n; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (preSums[i] - preSums[j] >= s) {
                    res = Math.min(res, i - j);
                    break;
                }
            }
        }

        return res;
    }


    /*
    Method3: 双指针，注意l是闭区间，r是开区间
     */
    public int minSubArrayLen3(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //如果只有一个数字，则其大于等于s则返回1，否则就能找到subarray sum，返回0。
        if (nums.length == 1) {
            if (s > nums[0]) {
                return 0;
            }
            else {
                return 1;
            }
        }

        //如果有数字大于s，则直接返回其数字即可，也就是subarray length为1.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= s) return 1;
        }

        int res = Integer.MAX_VALUE, sum = nums[0]; //res也可以取nums.length+1

        for (int l = 0, r = 0; r < nums.length; ) {
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            else {
                if (r == nums.length - 1) {
                    break;
                }
                r++;
                sum += nums[r];
            }
        }

        //不存在一个子数组的和大于等于s，按照题意置为0
        if (res == Integer.MAX_VALUE) {
            res = 0;
        }

        return res;
    }

    // add by tina,类似于方法2，连续长度子数组都可以用这个套路。
    // 也可以解释为双指针i,j套路
    public int minSubArrayLen4(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] sum = new int[nums.length];
        int j = 0;
        sum[0] = nums[0];
        if (nums[0] >= s) return 1;
        int minlen = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
            if (sum[i] >= s) {
                while (sum[i] - sum[j] >= s) {
                    j++;
                }
                minlen = Math.min(minlen, i - j + 1);
            }
        }

        return minlen == Integer.MAX_VALUE ? 0 : minlen; //边界容易错
    }

    @Test
    public void test() {
        System.out.println(minSubArrayLen3(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
