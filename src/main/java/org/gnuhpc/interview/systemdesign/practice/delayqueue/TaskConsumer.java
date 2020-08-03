package org.gnuhpc.interview.systemdesign.practice.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Copyright gnuhpc 19-8-24
 */

public class TaskConsumer implements Runnable {
    private final DelayQueue<Task> q;

    public TaskConsumer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = q.take();
                System.out.println("Take " + task + ",now is " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
