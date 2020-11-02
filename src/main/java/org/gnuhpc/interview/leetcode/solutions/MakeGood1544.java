package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/10/30
 */
public class MakeGood1544 {
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();

        if (s.length() == 1) return s;

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(arr[i]);
            } else {
                char first = stack.peek();
                if (Math.abs(arr[i] - first) != 32) {
                    stack.push(arr[i]);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            try {
                sb.append(stack.pollLast());
            } catch (Exception e) {

            }
        }


        return sb.toString();
    }
}
