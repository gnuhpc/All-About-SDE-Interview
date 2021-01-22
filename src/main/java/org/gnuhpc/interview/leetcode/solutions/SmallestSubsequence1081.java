package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/12/20
 */
public class SmallestSubsequence1081 {
    public String smallestSubsequence(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        Deque<Character> stack = new LinkedList<>();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && stack.getLast() > c && count.get(stack.getLast()) > 0) {
                    seen.remove(stack.removeLast());
                }
                stack.addLast(c);
                seen.add(c);
            }
            count.put(c, count.get(c) - 1);
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.removeFirst());
        }
        return res.toString();
    }
}
