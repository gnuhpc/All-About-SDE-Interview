package org.gnuhpc.bigdata.concurrency.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by gnuhpc on 2017/6/22.
 */
public class ProducerConsumer {
    public static class Producer implements Runnable {
        private List<Integer> queue;
        private int next = 0;

        public Producer(List<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    System.out.println(Thread.currentThread().getName() + " is producing: " + next);
                    queue.add(next);
                    queue.notifyAll();
                    try {

                        System.out.println(Thread.currentThread().getName() + " will sleep 1 second...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // The thread must own the monitor on the queue to call notify
                }
                next++;
            }
        }
    }

    public static class Consumer implements Runnable {
        private List<Integer> queue;

        public Consumer(List<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.size() > 0) {
                        Integer number = queue.remove(queue.size() - 1);
                        System.out.println(Thread.currentThread().getName() + " is consuming: " + number);
                    } else {
                        try {
                            // The thread must own queue’s monitor to call wait
                            System.out.println(Thread.currentThread().getName() + " is waiting...");
                            queue.wait();
                            System.out.println(Thread.currentThread().getName() + " is awaking...");
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        List<Integer> queue = new ArrayList<>();
        Thread producer1 = new Thread(new Producer(queue),"producer1");
        Thread producer2 = new Thread(new Producer(queue),"producer2");
        Thread consumer1 = new Thread(new Consumer(queue),"consumer1");
        Thread consumer2 = new Thread(new Consumer(queue),"consumer2");
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
