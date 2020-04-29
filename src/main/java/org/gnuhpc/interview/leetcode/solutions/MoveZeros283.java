package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.gnuhpc.interview.leetcode.utils.Utils.swap;

public class MoveZeros283 {
    //O(n) Time, O(1) Space
    public static void moveZerosInPlace(int[] nums) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        while (k < nums.length) {
            nums[k++] = 0;
        }
    }

    //最优解
    public static void moveZeroSwap(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) swap(nums, i, k++);
        }
    }

    //add by tina,思路与上面类似，但下面这个写法更容易想到
    // 首先找到第一个0元素，然后将后面的非0元素与当前数组第一个0元素交换
    // [i,cur)为0元素，注意边界的控制
    public void moveZeroes(int[] nums) {
        int i = 0;
        int cur = 0;
        while (cur < nums.length && nums[cur] != 0) cur++; // 找到第一个为0的元素
        i = cur;

        while (cur < nums.length && i < nums.length) {
            if (nums[cur] != 0) {
                swap(nums, i, cur);
                i++;
            }
            cur++;
        }
    }


    public static void main(String[] args) {

        int[] nums = new int[]{0, 1, 3, 0, 5, 0, 0, 1};
        int[] nums2 = new int[]{0, 1, 3, 0, 5, 0, 0, 1};
        int[] nums3 = new int[]{0, 1, 3, 0, 5, 0, 0, 1};

        moveZerosInPlace(nums2);
        moveZeroSwap(nums3);
        Arrays.stream(nums3).forEach(System.out::println);
        System.out.println(
                Arrays.stream(nums)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(""))
                        .equals(
                                Arrays.stream(nums2)
                                        .mapToObj(Integer::toString)
                                        .collect(Collectors.joining(""))
                        )
        );
    }
}
