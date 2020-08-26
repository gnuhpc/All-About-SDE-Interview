package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class IsValid20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        //如果为奇数，不用运行，直接为false
        if (s.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topStack = stack.pop();
                if (topStack != '(' && c == ')') {
                    return false;
                }
                if (topStack != '[' && c == ']') {
                    return false;
                }
                if (topStack != '{' && c == '}') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        isValid("()");
    }
}
