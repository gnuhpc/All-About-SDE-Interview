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
        elementsStack.push(x);
        if (minStack.isEmpty() || x<=minStack.peek()){
            minStack.push(x);
        }
    }

    public void pop() {
        int ePop = elementsStack.pop();
        //如果需要更新最小值stack,则pop最小值stack
        if (!minStack.isEmpty() && ePop == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return elementsStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
