package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;

/**
 * Copyright gnuhpc 2021/1/23
 */
public class KthLargest703 {
    private final PriorityQueue<Integer> queue;
    private final int limit;

    public KthLargest703(int k, int[] nums) {
        limit = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < limit) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }

        return queue.peek();
    }
}
