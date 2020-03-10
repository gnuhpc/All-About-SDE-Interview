package org.gnuhpc.interview.concurrency.producerconsumer.modernpattern;

import java.util.concurrent.Exchanger;

/**
 * Created by gnuhpc on 2017/6/22.
 */
public class ProducerConsumerWithExchanger {
    public static class Producer implements Runnable {
        private Exchanger<Integer> exchanger;
        private int next = 0;

        public Producer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    exchanger.exchange(next);
                    System.out.println("Producing:" + next);
                } catch (InterruptedException e) {
                }
                next++;
            }
        }
    }

    public static class Consumer implements Runnable {
        private Exchanger<Integer> exchanger;

        public Consumer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Consuming: " + exchanger.exchange(0));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String args[]) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread producer1 = new Thread(new Producer(exchanger));
        Thread consumer1 = new Thread(new Consumer(exchanger));
        producer1.start();
        consumer1.start();
    }
}
