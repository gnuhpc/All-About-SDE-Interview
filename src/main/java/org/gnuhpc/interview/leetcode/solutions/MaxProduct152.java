package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class MaxProduct152 {
    public int maxProduct(int[] nums) {
        if (nums == null && nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        return findMax(nums, 0, n);
    }

    private int findMax(int[] nums, int start, int end) {
        //递归退出条件
        if (start == end) return 0;//一个元素都没有
        if (start + 1 == end) {//有一个元素就返回这个元素，后者是0.
            return nums[start]>0?nums[start]:0;
        }

        //有0存在的进行递归
        for (int i = start; i < end; i++) {
            if (nums[i] == 0) {
                return Math.max(
                        findMax(nums, start, i),
                        findMax(nums, i + 1, end)); //跳过0，左闭右开
            }
        }

        int numOfNeg = 0;
        for (int i = start; i < end; i++) {
            if (nums[i] < 0) {
                numOfNeg++;
            }
        }
        /*
        当一个数组中没有0存在，则分为两种情况：
        1.负数为偶数个，则整个数组的各个值相乘为最大值；
        2.负数为奇数个，则从左边开始，乘到最后一个负数停止有一个“最大值”，从右边也有一个“最大值”，
        比较，得出最大值。
        */
        if (numOfNeg % 2 == 0) {
            int res = 1;
            for (int i = start; i < end; i++) {
                res *= nums[i];
            }

            return res;
        } else {
            int n = 0; //记录遍历中遇到的负数个数
            int res1 = 1;
            int res2 = 1;

            for (int i = start; i < end; i++) {
                if (nums[i] < 0) n++;
                if (n < numOfNeg) {
                    res1 *= nums[i];
                } else {
                    break;
                }
            }

            n = 0;
            for (int i = end - 1; i >= 0 && n < numOfNeg; i--) {
                if (nums[i] < 0) n++;
                if (n < numOfNeg) {
                    res2 *= nums[i];
                } else {
                    break;
                }
            }

            return Math.max(res1, res2);
        }
    }

    @Test
    public void test() {
        //System.out.println(maxProduct(new int[]{2,3,-2,4}));
        //System.out.println(maxProduct(new int[]{0,1,0}));
        //System.out.println(maxProduct(new int[]{-1,-2,-3,0}));
        System.out.println(maxProduct(new int[]{-2,0}));
    }

    //DP 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--36/
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    // add by tina, 暴力解法
    public int maxProduct3(int[] nums) {
        int N = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; ++i) {
            int cur = 1;
            for (int j = i; j < N; ++j) {

                cur = cur * nums[j];
                res = Math.max(res, cur);
            }
        }
        return res;
    }

}
