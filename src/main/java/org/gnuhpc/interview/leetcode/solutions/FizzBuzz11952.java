package org.gnuhpc.interview.leetcode.solutions;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class FizzBuzz11952 {
    private int n;
    private Semaphore spNumber = new Semaphore(1);
    private Semaphore spFizz = new Semaphore(0);
    private Semaphore spBuzz = new Semaphore(0);
    private Semaphore spFizzBuzz = new Semaphore(0);

    public FizzBuzz11952(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                spFizz.acquire();
                printFizz.run();
                spNumber.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 0; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                spBuzz.acquire();
                printBuzz.run();
                spNumber.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                spFizzBuzz.acquire();
                printFizzBuzz.run();
                spNumber.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            spNumber.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                spFizzBuzz.release();
            } else if (i % 3 == 0) {
                spFizz.release();
            } else if (i % 5 == 0) {
                spBuzz.release();
            } else {
                printNumber.accept(i);
                spNumber.release();
            }
        }
    }
}

