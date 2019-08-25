package org.gnuhpc.bigdata.systemdesign.taskscheduler;

import java.util.concurrent.DelayQueue;

/**
 * Copyright gnuhpc 19-8-24
 */
public class TaskSchedulerApp {
    public static void main(String[] args) {
        DelayQueue<Task> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}
