package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

public class CalPoints682 {
    public int calPoints(String[] ops) {
        Deque<Integer> nums = new LinkedList<>();
        for (String op: ops) {
            if (op.equals("C")) {
                nums.pollLast();
            } else if (op.equals("D")) {
                nums.offer(nums.peekLast() * 2);
            } else if (op.equals("+")) {
                int a = nums.pollLast();
                int b = nums.peekLast();
                int sum = a + b;
                nums.offer(a);
                nums.offer(sum);
            } else {
                nums.offer(Integer.valueOf(op));
            }
        }
        int sum = 0;
        for (Integer i: nums) {
            sum += i;
        }
        return sum;
    }
}
