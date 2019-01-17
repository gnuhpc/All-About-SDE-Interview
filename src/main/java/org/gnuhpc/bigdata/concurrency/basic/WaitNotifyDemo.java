package org.gnuhpc.bigdata.concurrency.basic;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-11-18 22:14
 * Version: 0.1
 */
//注意notify不解除sync，但是wait解除
// wait/notify必须在sync的里面进行
class Processor {

    public void produce() throws InterruptedException {

        synchronized (this) {
            System.out.println("We are in the producer method...");
            wait(10000); // release the key, and then put the thread into WAIT start
            System.out.println("Again producer method...");
        }
    }

    public void consume() throws InterruptedException {

        Thread.sleep(1000);

        synchronized (this) {
            System.out.println("Consumer method...");
            notify(); // release a WAIT thread randomly
            //notifyAll();
            Thread.sleep(3000);
        }
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
