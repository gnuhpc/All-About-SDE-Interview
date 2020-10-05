package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class WiggleSort280 {
    //add by tina,

    /**
     * 们先来看一种时间复杂度为O(nlgn)的方法，
     * 思路是先给数组排个序，
     * 然后我们只要每次把第三个数和第二个数调换个位置，
     * 第五个数和第四个数调换个位置，以此类推直至数组末尾，
     * 这样我们就能完成摆动排序了
     *
     * @param nums
     */

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) return;
        for (int i = 2; i < nums.length; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i - 1];
            nums[i - 1] = temp;
        }

    }


    // add by tina

    /**
     * 根据题目要求的nums[0] <= nums[1] >= nums[2] <= nums[3]....，我们可以总结出如下规律：
     * <p>
     * 当i为奇数时，nums[i] >= nums[i - 1]
     * <p>
     * 当i为偶数时，nums[i] <= nums[i - 1]
     * <p>
     * 那么我们只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可，
     *
     * @param nums
     */
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length < 2) return;

        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && (nums[i] < nums[i - 1])) || (i % 2 == 0) && (nums[i] > nums[i - 1])) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
    }


    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test() {
        wiggleSort2(new int[]{1, 3, 4, 5, 7, 2, 8, 9, 19, 11});
    }

}
