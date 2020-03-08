package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

public class Rotate189 {
    /**
     * 翻转字符
     * reverse the whole array O(n) [7,6,5,4,3,2,1]
     * reverse the left part 0 ~ k – 1 O(k) [5,6,7,4,3,2,1]
     * reverse the right part k ~ n – 1 O(n-k) [5,6,7,1,2,3,4]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length == 0) return;

        k %= nums.length;
        reverseArray(nums, 0, nums.length);
        reverseArray(nums, 0, nums.length - k);
        reverseArray(nums, nums.length - k, nums.length);

    }

    private void reverseArray(int[] nums, int left, int right) {
        if (nums == null || nums.length <= 1 || left < 0 || right > nums.length) {
            return;
        }

        for (int i = left, j = right - 1; i < j; i++, j--) {
            swapElements(nums, i, j);
        }
    }

    private void swapElements(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 2};
        // expect: [5,6,7,1,2,3,4]
        rotate(arr, 3);
        Utils.printArray(arr);
    }

    // add by Tina，暴力解法，每次向右旋转一轮，进行k轮
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1 || k <= 0) return;
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }
}
