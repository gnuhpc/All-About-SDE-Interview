package org.gnuhpc.interview.concurrency.basic;

import java.util.concurrent.*;

/*
From https://dzone.com/articles/understanding-thread-interruption-in-java
In Java, one thread cannot stop the other thread.
A thread can only request the other thread to stop.
The request is made in the form of an interruption.
Calling the interrupt() method on an instance of aThread sets the interrupt status state as true on the instance.
 */

//总结一句话就是interrupt()+volatile boolean
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

    /*
    要求每一个方法的调用方有义务去处理异常。调用方要不使用 try/catch 并在 catch 中正确处理异常，要不将异常声明到方法签名中。
    如果每层逻辑都遵守规范，便可以将中断信号层层传递到顶层，最终让 run() 方法可以捕获到异常。
    而对于 run() 方法而言，它本身没有抛出 checkedException 的能力，
    只能通过 try/catch 来处理异常。层层传递异常的逻辑保障了异常不会被遗漏，
    而对 run() 方法而言，就可以根据不同的业务逻辑来进行相应的处理。
     */
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

    /*
     stop() 会直接把线程停止，这样就没有给线程足够的时间来处理想要在停止前保存数据的逻辑，任务戛然而止，会导致出现数据完整性等问题。
    而对于 suspend() 和 resume() 而言，它们的问题在于如果线程调用 suspend()，它并不会释放锁，就开始进入休眠，但此时有可能仍持有锁，这样就容易导致死锁问题，因为这把锁在线程被 resume() 之前，是不会被释放的。
     */
}
