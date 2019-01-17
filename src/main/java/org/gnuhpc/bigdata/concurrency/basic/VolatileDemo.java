package org.gnuhpc.bigdata.concurrency.basic;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-11-18 21:46
 * Version: 0.1
 */

class Worker implements Runnable {

    private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while(!isTerminated) {

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
