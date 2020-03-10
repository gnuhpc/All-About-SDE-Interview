package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class TwoSumII167 {
    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 15};
        Utils.printArray(findTwoSumII2(nums, 9));
    }

    //有序数组的对撞指针解法
    public int[] findTwoSumII(int[] numbers, int target) {
        int[] result = new int[2];
        int i = 0, j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return result;
    }

    public int[] findTwoSumII2(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            int pos = binarySearch(numbers, i + 1, numbers.length - 1, target - n);
            if (pos > i) {
                result[0] = i + 1;
                result[1] = pos + 1;
                break;
            }
        }
        return result;
    }

    private int binarySearch(int[] numbers, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] == target) {
                right = mid;
            } else if (numbers[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (numbers[left] == target) return left;
        if (numbers[right] == target) return right;

        return -1;
    }
}
