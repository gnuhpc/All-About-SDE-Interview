package org.gnuhpc.interview.leetcode.solutions;

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
            } else {
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

    /*
    Method3: 单调栈
     */

    public boolean find132pattern3(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        //单调栈(栈底到栈顶的元素大小是递减的)，保持栈顶始终为132中的3
        Deque<Integer> stack = new LinkedList<>();
        //存储132中的2
        int last = Integer.MIN_VALUE;
        //从后往前遍历，先确定2和3
        for (int i = n - 1; i >= 0; i--) {
            //当找到了132中的1时，返回
            if (nums[i] < last) return true;
            /* 当当前值大于等于栈顶元素，则循环出栈并将值更新到2中使2为最大3的次小数（为1的选择留下广阔空间）
             * 然后将当前元素作为新的3。更新的结果是：3为当前遍历到的最大元素，2为小于当前3的最大元素。
             */
            while (!stack.isEmpty() && nums[i] > stack.peek()) last = stack.pop();
            //当前值入栈作为新的备选3
            stack.push(nums[i]);
        }
        return false;
    }
}
