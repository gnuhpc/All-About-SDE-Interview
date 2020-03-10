package org.gnuhpc.interview.concurrency.producerconsumer.rawpattern.allinclient;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-11-18 22:14
 * Version: 0.1
 */
//注意notify不解除sync，但是wait解除
// wait/notify必须在sync的里面进行

class Producer implements Runnable {
    private final Queue<Integer> taskQueue;
    private final int MAX_CAPACITY;

    public Producer(Queue<Integer> sharedQueue, int size) {
        this.taskQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                produce(counter++);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        synchronized (taskQueue) { //使用Synchronized进行同步，其关键就是必须要对对象的监视器monitor进行获取，当线程获取monitor后才能继续往下执行，否则就只能等待。
            while (taskQueue.size() == MAX_CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " +
                        taskQueue.size());
                taskQueue.wait();//要执行某个实例的wait方法必须已经获取此事例的锁monitor
                // wait后就释放了这个锁，把自己放入了等待队列
            }

            Thread.sleep(1000);
            taskQueue.offer(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();// 会让等待队列中的线程退出等待队列，但是在实际执行中还要去抢锁。
        }
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> taskQueue;

    public Consumer(Queue<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    while (守护条件的逻辑非){
        wait
    }

    执行目标处理
    notifyAll
     */
    private synchronized void consume() throws InterruptedException {
        synchronized (taskQueue) {//不满足条件就while wait
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " +
                        taskQueue.size());
                taskQueue.wait();
            }
//            Thread.sleep(1000);
            int i = taskQueue.poll();
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll(); //做完就notify,相当于放弃taskQueue monitor
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Queue<Integer> taskQueue = new LinkedList<>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
    }
}

