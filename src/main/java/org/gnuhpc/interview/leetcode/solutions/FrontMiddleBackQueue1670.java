package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Copyright gnuhpc 2021/1/12
 */
public class FrontMiddleBackQueue1670 {
    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue1670() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        left.addFirst(val);
        reBalance();
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size()) {
            right.addFirst(val);
        } else {
            left.addLast(val);
        }
    }

    public void pushBack(int val) {
        right.addLast(val);
        reBalance();
    }

    public int popFront() {
        Integer integer = left.pollFirst();
        if (integer == null) {
            integer = right.pollFirst();
            return integer == null ? -1 : integer;
        } else {
            reBalance();
            return integer;
        }
    }

    public int popMiddle() {
        if (left.size() == right.size()) {
            Integer integer = left.pollLast();
            return integer == null ? -1 : integer;
        } else {
            return right.pollFirst();
        }
    }

    public int popBack() {
        Integer integer = right.pollLast();
        reBalance();
        return integer == null ? -1 : integer;
    }

    public void reBalance() {
        if (left.size() > right.size()) {
            right.addFirst(left.pollLast());
        } else if (right.size() == left.size() + 2) {
            left.addLast(right.pollFirst());
        }
    }
}
