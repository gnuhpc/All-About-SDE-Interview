package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class BackspaceCompare844 {
    public boolean backspaceCompare(String S, String T) {
        Deque<Character> s1 = getString(S);
        Deque<Character> s2 = getString(T);

        if(s1.size() != s2.size()) {
            return false;
        }
        if(s1.size() == 0 && s2.size() == 0) {
            return true;
        }
        while(!s1.isEmpty() && !s2.isEmpty()) {
            if(s1.pop() != s2.pop()) {
                return false;
            }
        }
        return true;
    }

    public Deque getString(String str) {
        char[] chs = str.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] != '#') {
                stack.push(chs[i]);
            } else {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack;
    }
}
