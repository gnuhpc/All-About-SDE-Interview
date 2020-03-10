package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class IsValid20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        stack.push(' ');//put dummy to simplified the right condition

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLeft(c)) {
                stack.push(c);
            }

            if (isRight(c)) {
                if (!isMatch(stack.pop(), c)) return false;
            }
        }

        if (stack.size() == 1) {//only dummy
            return true;
        } else {
            return false;
        }
    }

    private boolean isMatch(char l, char r) {
        if ((l == '(' && r == ')') ||
                (l == '{' && r == '}') ||
                (l == '[' && r == ']')) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRight(char c) {
        return c == '}' || c == ')' || c == ']';
    }

    private boolean isLeft(char c) {
        return c == '{' || c == '(' || c == '[';
    }

    @Test
    public void test() {
        isValid("()");
    }
}
