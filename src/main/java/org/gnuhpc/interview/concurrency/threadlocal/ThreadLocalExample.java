package org.gnuhpc.interview.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Copyright gnuhpc 2020/8/21
 */

public class ThreadLocalExample implements Runnable {
    // SimpleDateFormat is not thread-safe, so give one to each thread
    private static final ThreadLocal<SimpleDateFormat> formatter =
            ThreadLocal.withInitial(() ->
                    new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        //一个runnable，多次start
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }


}