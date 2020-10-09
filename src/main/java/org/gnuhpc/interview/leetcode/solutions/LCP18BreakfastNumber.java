package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/10/5
 */
public class LCP18BreakfastNumber {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        // 这里采用了一个小技巧。使第一个数组为 max(staple,drinks),这样可以提高计算速度。
        if (staple.length > drinks.length) {
            return breakfastNumber(drinks, staple, x);
        }
        //经过处理，代码走到这里，staple 的长度是小于 drinks 长度的。
        //使用二分查找的前提是，数的集合有序，故使用 Arrays 类的静态方法将两个数组排序。
        Arrays.sort(staple);
        Arrays.sort(drinks);
        // 定义一个整型变量，存放方案的数量。
        int count = 0;
        //  取出 staple[i]
        for (int i = 0; i < staple.length; i++) {
            // 标记 x - staple[i] ，这是我们要去 drinks 数组找的元素（此元素后面的不满足方案要求），用 tmpIdx 接收 binarySearch() 方法返回满足方案的最大下标。
            int tmpIdx = leftBound(x - staple[i], drinks);
            // 方案累和器（包括0位置，故 +1），并取题设要求的 mod 。
            count = (count + tmpIdx + 1) % 1000000007;
        }
        return count;
    }

    public int leftBound(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (target <= nums[0]) return -1;
        if (target >= nums[nums.length - 1]) return nums.length - 1;

        int start = 0;
        int end = nums.length - 1;
        //相邻就退出
        while (start + 1 < end) {
            //不会越界
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                end = mid; //往前找
                //If you'd like to fetch the last idx of the target, use the following statement instead:
//                start = mid; //往后找
            } else if (target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //范围缩小，double check
        // 最后范围内就剩下start和end两个
        if (target >= nums[end]) {
            return end;
        } else { // target between nums[start], nums[end]
            return start;
        }
    }

    @Test
    public void test() {
        System.out.println(breakfastNumber(new int[]{10, 20, 5}, new int[]{5, 5, 2}, 15));
    }
}
