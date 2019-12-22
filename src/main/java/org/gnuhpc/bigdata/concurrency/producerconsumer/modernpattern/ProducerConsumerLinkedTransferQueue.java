package org.gnuhpc.bigdata.concurrency.producerconsumer.modernpattern;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-11-18 22:14
 * Version: 0.1
 */
/*
 The basic difference between TransferQueue and BlockingQueue is that TransferQueue waits for consumer to consume the element.
 TransferQueue is useful in scenario where message passing need to be guaranteed.
 TransferQueue makes it possible because producer will wait for consumer to transfer the message.
 The main method of TransferQueue is transfer(E e) .
 http://poltora.info/blog/synchronousqueue-vs-transferqueue/

 works on the principle «one came in — one went out»
 */

public class ProducerConsumerLinkedTransferQueue{
    static LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
    @Test
    public void test() throws InterruptedException {
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        exService.execute(producer);
        exService.execute(consumer);

        exService.awaitTermination(1000, TimeUnit.SECONDS);
    }
    class Producer implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<3;i++){
                try {
                    System.out.println("Producer is waiting to transfer...");
                    queue.transfer("A"+i);
                    System.out.println("producer transfered element: A"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Consumer implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<3;i++){
                try {
                    System.out.println("Consumer is waiting to take element...");
                    String s= queue.take();
                    System.out.println("Consumer received Element: "+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

