package org.gnuhpc.interview.systemdesign.practice.taskscheduler;

import java.util.concurrent.DelayQueue;

/**
 * Copyright gnuhpc 19-8-24
 */

public class TaskConsumer implements Runnable {
    private DelayQueue<Task> q;

    public TaskConsumer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = q.take();
                System.out.println("Take " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
