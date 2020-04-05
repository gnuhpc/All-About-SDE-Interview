package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/4/5
 */
public class RemoveDuplicates1047 {
    public String removeDuplicates(String S) {
        char[] sChars = S.toCharArray();

        Deque<Character> stack = new LinkedList<>();

        for (int i = sChars.length - 1; i >= 0; i--) {
            if (stack.size() == 0) {
                stack.push(sChars[i]);
                continue;
            }
            // stack is not Empty
            if (sChars[i] == stack.peek()) {
                stack.pop();
            } else {
                stack.push(sChars[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
