package org.gnuhpc.interview.concurrency.basic;

import java.util.concurrent.*;

/*
From https://dzone.com/articles/understanding-thread-interruption-in-java
In Java, one thread cannot stop the other thread.
A thread can only request the other thread to stop.
The request is made in the form of an interruption.
Calling the interrupt() method on an instance of aThread sets the interrupt status state as true on the instance.
 */
public class StopThread extends Thread {
    private volatile boolean shutdownRequested = false;

    public final void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (!shutdownRequested) {
                doWork();
            }

        } catch (InterruptedException e) {
            System.out.println("Shutdown request received");

        } finally {
            doShutdown();
        }

    }

    private void doShutdown() {
        System.out.println("Really shut down");
    }

    private void doWork() throws InterruptedException {
        System.out.println("Working!!!");
        sleep(100000);
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread t = new StopThread();
        t.start();
        Thread.sleep(3000);
        t.shutdownRequest();
        t.join();
    }
}
