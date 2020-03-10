package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class StackByQueue225 {
    private Queue<Integer> internalQueue;

    /**
     * Initialize your data structure here.
     */
    public StackByQueue225() {
        this.internalQueue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        internalQueue.add(x);
        int size = internalQueue.size();
        for (int i = 0; i < size - 1; i++) {
            internalQueue.add(internalQueue.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return internalQueue.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return internalQueue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return internalQueue.isEmpty();
    }
}
