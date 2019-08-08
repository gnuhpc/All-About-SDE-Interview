package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

/**
 * Copyright gnuhpc 19-8-8
 */
public class WiggleSort324 {
    public void wiggleSort(int[] nums) {
        if (nums == null) return;
        if (nums.length <= 1) return;
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                swap(nums, 0, 1);
            }
            return;
        }

        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        double average = (double) sum / (double) n;

        partition(nums, average);

        int line = 0;
        if (n % 2 == 0) {
            line = n / 2;
        }
        else {
            line = (n + 1) / 2;
        }


        boolean flag = true;
        int[] res = reconstruct(nums, line, true);
        if (!isWiggle(res)) {
            res = reconstruct(nums, line, false);
        }


        System.arraycopy(res, 0, nums, 0, n);
    }

    private int[] reconstruct(int[] nums, int line, boolean flag) {
        int[] res = new int[nums.length];
        for (int k = 0, i = 0, j = line; k < nums.length; k++) {
            if (flag) {
                res[k] = nums[i];
                i++;
            }
            else {
                res[k] = nums[j];
                if (j < nums.length) j++;
            }
            flag = !flag;
        }
        return res;
    }

    private void partition(int[] A, double pivot) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            while (A[i] < pivot && i < j) i++;
            while (A[j] >= pivot && i < j) j--;

            swap(A, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    boolean isWiggle(int[] nums) {
        boolean flag1 = true, flag2 = true;
        for (int i = 1; i < nums.length - 1; i += 2) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) continue;
            else {
                flag1 = false;
                break;
            }
        }

        if (flag1) return true;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) continue;
            else {
                flag2 = false;
                break;
            }
        }

        return flag2;
    }


    @Test
    public void test() {
//        int[] nums = new int[] {1,1,2,1,2,2,1};
//        int[] nums = new int[] {1,3,2,2,3,1};
        int[] nums = new int[]{5, 3, 1, 2, 6, 7, 8, 5, 5};
//        int[] nums = new int[] {4,5,5,6};
//        int[] nums = new int[] {4,5,5,5,5,6,6,6};
        wiggleSort(nums);
        Utils.printArray(nums);
    }
}
