package org.gnuhpc.interview.concurrency.alternatively;

import org.junit.Test;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright gnuhpc 2020/8/9
 */
public class OddEven {

    public static void main(String[] args) {

        Printer printer = new Printer();

        MyRunnable r1 = new MyRunnable(true, printer);// isOdd = true
        Thread t1 = new Thread(r1);
        MyRunnable r2 = new MyRunnable(false, printer);// isOdd = false
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}

class Printer {
    private volatile boolean isOdd = false;

    public void printEven(int number) throws InterruptedException {
        synchronized (Printer.class) {
            while (isOdd == false) {
                Printer.class.wait();
            }
            System.out.println("even : " + number);
            isOdd = false;
            Printer.class.notifyAll();
        }
    }

    public void printOdd(int number) throws InterruptedException {
        synchronized (Printer.class) {
            while (isOdd == true) {
                Printer.class.wait();
            }
            System.out.println("odd : " + number);
            isOdd = true;
            Printer.class.notifyAll();
        }
    }
}

class MyRunnable implements Runnable {

    private final boolean isOdd;
    Printer printer;

    MyRunnable(boolean isOdd, Printer printer) {
        this.isOdd = isOdd;
        this.printer = printer;
    }

    public void run() {
        int number = isOdd == true ? 1 : 2;
        while (number <= 10) {
            if (isOdd) {
                try {
                    printer.printOdd(number);
                } catch (InterruptedException e) {
                }
            } else {
                try {
                    printer.printEven(number);
                } catch (InterruptedException e) {
                }
            }
            number += 2;
        }
    }
}
