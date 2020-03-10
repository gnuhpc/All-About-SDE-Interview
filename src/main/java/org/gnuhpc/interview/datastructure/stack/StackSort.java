package org.gnuhpc.interview.datastructure.stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class StackSort {
    public Deque<Integer> sort(Deque<Integer> stack) {
        Deque<Integer> tempStack = new LinkedList<>();

        while (!stack.isEmpty()) {
            int top = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() >= top) {
                stack.push(tempStack.pop());
            }

            tempStack.push(top);
        }

        return tempStack;
    }

    @Test
    public void test() {
        Deque<Integer> testStack = new LinkedList<>();
        testStack.push(1);
        testStack.push(3);
        testStack.push(2);
        testStack.push(5);
        testStack.push(8);
        testStack.push(6);

        sort(testStack).forEach(System.out::println);
    }
}
