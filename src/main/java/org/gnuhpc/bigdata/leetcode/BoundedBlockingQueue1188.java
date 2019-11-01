package org.gnuhpc.bigdata.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class BoundedBlockingQueue1188 {
    private int            maxLength;
    private Queue<Integer> queue;
    public BoundedBlockingQueue1188(int capacity) {
        this.maxLength = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (this){
            while (queue.size() == maxLength){
                this.wait();
            }

            queue.offer(element);
            this.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (this){
            while(queue.isEmpty()){
                this.wait();
            }

            int res = queue.poll();
            this.notifyAll();
            return res;
        }
    }

    public int size() {
        return queue.size();
    }
}
