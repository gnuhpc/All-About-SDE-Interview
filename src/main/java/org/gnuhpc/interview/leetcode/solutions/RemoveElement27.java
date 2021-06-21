package org.gnuhpc.interview.leetcode.solutions;

public class RemoveElement27 {
    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 1, 3, 0, 4, 2};
        int size = removeElement(nums, 2);
        System.out.println("Size is :" + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }

    }
}
