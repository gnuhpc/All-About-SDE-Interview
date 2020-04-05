package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/4/5
 */
public class CQueue09 {
    Deque<Integer> input = new LinkedList<>();
    Deque<Integer> output = new LinkedList<>();

    public CQueue09() {
    }

    public void appendTail(int value) {
        input.push(value);
    }

    public int deleteHead() {
        if (isEmpty()) return -1;
        if (output.isEmpty())
            while (!input.isEmpty())
                output.push(input.pop());
        return output.pop();
    }

    private boolean isEmpty() {
        return input.isEmpty() && output.isEmpty();
    }
}
