package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

public class ProductExceptSelf238 {

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < len; i++) {
            nums[i] = left[i] * right[i];
        }
        return nums;
    }

    // add by tina 常量空间复杂度
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length, right = 1;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; --i) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    @Test
    public void test() {
        Utils.printArray(productExceptSelf(new int[]{1, 2, 3}));
    }
}
