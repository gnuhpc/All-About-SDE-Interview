package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/8/18
 */
/*
举个例子：1 2 3. 以3为开头的符合题意的子数组有1个（len-2（3的idx）），以2为开头的符合题意的子数组为2个（len - 1(2的idx)）
 */
public class ValidSubarrays1063 {
    public int validSubarrays(int[] nums) {
        Deque<Integer> s = new LinkedList<>();
        int sum = 0, i = 0;
        while (i < nums.length) {
            if (s.isEmpty() || nums[s.peek()] <= nums[i])//可能会更大，因此往后走
                s.push(i++);
            else {
                //另外注意在处理最值的时候是不动i的
                sum += i - s.pop();  // 为了维护单调递减(从顶部往下看)放入新元素，要先处理大的栈顶
            }
        }
        while (s.size() > 0) {//现在留在栈里的元素下标代表的元素，其右边到头都没有比它小的
            sum += (nums.length - s.pop());
        }
        return sum;
    }

    @Test
    public void test() {
        System.out.println(validSubarrays(new int[]{1, 4, 2, 5, 3}));
    }
}
