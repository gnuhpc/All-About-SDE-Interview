package org.gnuhpc.bigdata.leetcode;

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
        right[len-1] = 1;
        for (int i = len-2; i >=0 ; i--) {
            right[i] = right[i+1] * nums[i+1];
        }

        for (int i = 0; i < len; i++) {
            nums[i] = left[i]*right[i];
        }
        return nums;
    }

    @Test
    public void test(){
        Utils.printArray(productExceptSelf(new int[]{1,2,3}));
    }
}
