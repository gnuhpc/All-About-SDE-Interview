package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/5/9
 */
public class StockSpanner901 {
    Deque<Integer> s = new LinkedList<>();
    List<Integer> cache = new ArrayList<>();
    int index = 0;

    public StockSpanner901() {
    }

    public int next(int price) {
        cache.add(price);
        return days(cache, price);
    }

    private int days(List<Integer> cache, int price) {
        while (!s.isEmpty() && cache.get(s.peek()) <= price) {
            s.pop();
        }
        int res = s.isEmpty() ? index + 1 : index - s.peek();
        s.push(index++);
        return res;
    }

    @Test
    public void test() {
        StockSpanner901 ss = new StockSpanner901();
        System.out.println(ss.next(1000));
        System.out.println(ss.next(700));
        System.out.println(ss.next(500));
        System.out.println(ss.next(600));
        System.out.println(ss.next(650));
        System.out.println(ss.next(625));
    }
}
