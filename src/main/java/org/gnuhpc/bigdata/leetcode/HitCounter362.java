package org.gnuhpc.bigdata.leetcode;

import sun.awt.X11.XKeysym;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Copyright gnuhpc 2019/10/5
 */
public class HitCounter362 {

    int WINDOW_SIZE = 300;

    Queue<Integer> q = null;

    /**
     * Initialize your data structure here.
     */
    public HitCounter362() {
        q = new LinkedList<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!q.isEmpty() && timestamp - q.peek() >= WINDOW_SIZE) {
            q.poll();
        }
        return q.size();
    }


    public static void main(String[] args) {
        HitCounter362 counter = new HitCounter362();
        counter.hit(1);
        counter.hit(1);
        counter.hit(1);
        counter.hit(300);
        System.out.println(counter.getHits(300));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        counter.hit(301);
        System.out.println(counter.getHits(301));
    }
}
