package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 19-8-5
 */
public class MedianFinder2295 {
    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;
    private boolean even;

    public MedianFinder2295() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
        even = true;
    }

    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return small.peek();
    }

    public void addNum(int num) {//可以想象成两个heap队列将头部数据顶给对方
        if (even) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        even = !even;
    }

    @Test
    public void test() {
        addNum(1);
        System.out.println(findMedian());
        addNum(3);
        System.out.println(findMedian());
        addNum(-2);
        System.out.println(findMedian());
        addNum(1);
        System.out.println(findMedian());
        addNum(5);
        System.out.println(findMedian());
    }
}
