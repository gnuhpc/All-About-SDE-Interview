package org.gnuhpc.interview.leetcode.solutions;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class BoundedBlockingQueue11882 {
    private int maxLength;
    private BlockingQueue<Integer> queue;

    public BoundedBlockingQueue11882(int capacity) {
        this.maxLength = capacity;
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void enqueue(int element) throws InterruptedException {
        queue.put(element);
    }

    public int dequeue() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }
}
