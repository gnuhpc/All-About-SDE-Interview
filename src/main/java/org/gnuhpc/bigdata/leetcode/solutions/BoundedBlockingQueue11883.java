package org.gnuhpc.bigdata.leetcode.solutions;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class BoundedBlockingQueue11883 {
    private int            maxLength;
    private Queue<Integer> queue;
    private Semaphore      semaphore;
    private Semaphore      consumerSemaphore = new Semaphore(0);

    public BoundedBlockingQueue11883(int capacity) {
        this.maxLength = capacity;
        this.queue = new LinkedList<>();
        semaphore = new Semaphore(maxLength);
    }

    public void enqueue(int element) throws InterruptedException {
        semaphore.acquire();
        queue.add(element);
        consumerSemaphore.release();
    }

    public int dequeue() throws InterruptedException {
        consumerSemaphore.acquire();
        int res = queue.poll();
        semaphore.release();
        return res;
    }

    public int size() {
        return queue.size();
    }
}
