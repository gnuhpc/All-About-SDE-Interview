package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class CheckPossibility665 {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        if (n <= 1) return true;
        int down = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                down++;
                if (down > 1) {
                    return false;
                }
                //V型
                if (i > 1 && i < n - 1 && nums[i - 1] > nums[i + 1] && nums[i - 2] > nums[i]) {
                    return false;
                }
            }
        }
        return true;

    }

    @Test
    public void test() {
        int[] arr = {4, 2, 3};
        System.out.println(checkPossibility(arr));

        int[] arr2 = {3, 4, 2, 3};
        System.out.println(checkPossibility(arr2));

        int[] arr3 = {4, 2, 1};
        System.out.println(checkPossibility(arr3));
    }
}
