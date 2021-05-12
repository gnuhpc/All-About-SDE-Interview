package org.gnuhpc.interview.leetcode.solutions;

public class TripleInOne0301 {
    private int[] stack;
    private int[] stackInx = new int[3];
    private int stackSize;

    public TripleInOne0301(int stackSize) {
        this.stackSize = stackSize;
        stack = new int[stackSize * 3];
        stackInx[0] = 0; //标记三个栈起始位置
        stackInx[1] = stackSize;
        stackInx[2] = stackSize * 2;
    }

    public void push(int stackNum, int value) {
        if(stackInx[stackNum] < (stackNum + 1) * stackSize) { //当前栈未压满
            stack[stackInx[stackNum]++] = value;
        }
    }

    public int pop(int stackNum) {
        if(isEmpty(stackNum)) return -1;
        return stack[--stackInx[stackNum]]; //减少栈标记，弹出栈顶值
    }

    public int peek(int stackNum) {
        if(isEmpty(stackNum)) return -1;
        return stack[stackInx[stackNum] - 1]; //浮出栈顶值
    }

    public boolean isEmpty(int stackNum) {
        return stackInx[stackNum] == stackNum * stackSize;
    }
}
