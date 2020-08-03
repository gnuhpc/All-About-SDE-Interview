package org.gnuhpc.interview.systemdesign.practice.delayqueue;

/**
 * Copyright gnuhpc 19-8-24
 */

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

public class TaskProducer implements Runnable {
    private final Random random = new Random();
    private final DelayQueue<Task> q;

    public TaskProducer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int delay = random.nextInt(10000);
                Task task = new Task(UUID.randomUUID().toString(), delay);
                System.out.println("Put " + task + ", delay " + delay / 1000);
                q.put(task);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

