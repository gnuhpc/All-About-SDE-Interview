package org.gnuhpc.bigdata.leetcode;

import java.util.Stack;

public class MinStack155 {

    private Stack<Integer> minStack;
    private Stack<Integer> elementsStack;

    /** initialize your data structure here. */
    public MinStack155() {
        this.minStack = new Stack<>();
        this.elementsStack = new Stack<>();
    }

    public void push(int x) {
        if (minStack.isEmpty() || x<=minStack.peek()){
            minStack.push(x);
        }
        elementsStack.push(x);
    }

    public void pop() {
        if (!minStack.isEmpty() && elementsStack.peek()<=minStack.peek()){
            minStack.pop();
        }
        elementsStack.pop();
    }

    public int top() {
        return elementsStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
