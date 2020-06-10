package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Arrays;

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
        int n = nums.length, left = 1, right = 1;
        int[] res = new int[n];
        //累乘的初始值是1，就像累加的初始值是0一样。
        Arrays.fill(res, 1);

        //先算左边的
        //left是初始积累乘积的值，也就是1
        for (int i = 0; i < n; i++) {
            res[i] *= left;
            left *= nums[i];//为下一步累乘的结果做准备
        }

        //到此为止 res[i] 保存的是i左边所有数字的乘积
        //right是初始积累乘积的值，也就是1
        //下边该算右边的了，方法一致，上边懂了这边就懂了
        for (int i = n - 1; i >= 0; i--) {
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
