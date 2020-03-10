package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

public class QueueByStack232 {
    Deque<Integer> input = new LinkedList<>();
    Deque<Integer> output = new LinkedList<>();

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.isEmpty())
            while (!input.isEmpty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

}
