package org.gnuhpc.interview.concurrency.producerconsumer.modernpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gnuhpc on 2017/6/22.
 */
public class ProducerConsumerWithBlockingQueue {
    public static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;
        private AtomicInteger next = new AtomicInteger(0);

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(next.getAndIncrement());
                    System.out.println("producing: " + next);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Integer next;
                try {
                    next = queue.take();
                    System.out.println("consuming: " + next);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);
        Thread producer1 = new Thread(new Producer(queue));
//        Thread producer2 = new Thread(new Producer(queue));
        Thread consumer1 = new Thread(new Consumer(queue));
//        Thread consumer2 = new Thread(new Consumer(queue));
        producer1.start();
//        producer2.start();
        consumer1.start();
//        consumer2.start();
    }
}
