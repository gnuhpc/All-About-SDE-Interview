package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Copyright gnuhpc 2020/6/30
 */
public class Offer09Cqueue {
    Deque<Integer> s1 = new LinkedList<>();
    Deque<Integer> s2 = new LinkedList<>();

    public Offer09Cqueue() {

    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (s1.isEmpty() && s2.isEmpty()) return -1;
        if (!s2.isEmpty()) return s2.pop();
        else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            return s2.pop();
        }
    }
}
