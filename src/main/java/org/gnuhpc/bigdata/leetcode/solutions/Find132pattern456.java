package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Copyright gnuhpc 19-7-24
 */
public class Find132pattern456 {
    //Method 1: 暴力解法
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Integer low = null, high = null;
        int start = 0, end = 0;
        while (start < nums.length - 1) {
            while (start < nums.length - 1 && nums[start] >= nums[start + 1]) start++;
            // start is lowest now
            int m = start + 1;
            while (m < nums.length - 1 && nums[m] <= nums[m + 1]) m++;
            // m is highest now
            int j = m + 1;
            while (j < nums.length) {
                if (nums[j] > nums[start] && nums[j] < nums[m]) return true;
                j++;
            }
            start = m + 1;
        }
        return false;
    }

    //Method2: Stack
    public boolean find132pattern2(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>(); // push aj before ai (i < j, ai < aj)
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                min = num;
            }
            else {
                while (!stack.isEmpty()) {
                    if (stack.peek() >= num) break;
                    stack.pop();
                    if (stack.pop() > num) return true;
                }
                stack.push(num);
                stack.push(min);
            }
        }
        return false;
    }
}
