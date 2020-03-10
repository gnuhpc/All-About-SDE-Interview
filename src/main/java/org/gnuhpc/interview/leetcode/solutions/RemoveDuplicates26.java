package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class RemoveDuplicates26 {
    // 思路：双指针，一个指向当前非重复元素的最后一个位置，一个负责向前遍历
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;

        //l始终指向不重复数字的最后一个
        int l, r;

        for (l = 0, r = 1; r < nums.length; r++) {
            if (nums[r] != nums[l]) {
                nums[++l] = nums[r];
            }
        }

        return l + 1;
    }


    @Test
    public void test() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int size = removeDuplicates(nums);

        System.out.println("The result size is: " + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
    }
}
