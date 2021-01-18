package org.gnuhpc.interview.leetcode.solutions;

import java.util.Stack;

/**
 * Copyright gnuhpc 2021/1/12
 */
public class SortedStack0305 {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public SortedStack0305() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {
        if (stack1.isEmpty()) {
            stack1.push(val);
        } else {
            while (!stack1.isEmpty() && stack1.peek() < val) {
                stack2.push(stack1.pop());
            }
            stack1.push(val);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    public void pop() {
        if (stack1.isEmpty()) {
            return;
        }
        stack1.pop();
    }

    public int peek() {
        if (stack1.isEmpty()) {
            return -1;
        }
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
