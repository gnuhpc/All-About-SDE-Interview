package org.gnuhpc.interview.concurrency.basic;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-11-18 21:46
 * Version: 0.1
 */

/*
Volatile 控制写入的可见性,即线程A写入到字段中的值一定会被线程B可见，不只是共享变量，对于线程B中的变量也是如此
volatile字段读取和写入前后不会发生重排序
volatile 不进行线程互斥，不会进入等待队列，有性能代价，和synchronized基本一致

很常见的一个用法是long double 原子性是不无法保证的，因此一般用volatile修饰
 */
class Worker implements Runnable {

    private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while (!isTerminated) {

            System.out.println("Hello from worker class...");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);
        System.out.println("Finished...");
    }
}
