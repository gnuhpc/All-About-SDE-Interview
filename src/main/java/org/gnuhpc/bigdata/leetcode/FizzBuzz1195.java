package org.gnuhpc.bigdata.leetcode;

import java.util.function.IntConsumer;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class FizzBuzz1195 {
    private          int n;
    private volatile int i = 1;

    public FizzBuzz1195(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) { //要点1: 对外界条件放在最外边 ,因为每个方法都只调用自己的线程调用一次
                while (i % 3 != 0 || i % 15 == 0) {
                    wait();
                    // 要点2: 在这里要判断是不是满足条件,否则会进入死循环
                    // 见https://leetcode.com/problems/fizz-buzz-multithreaded/discuss/385395/Java-using-synchronized-with-short-explanation.
                    if (i > n) break;
                }
                if (i > n) break;// 要点3 这是要跳出这一层的循环
                printFizz.run();
                i++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 5 != 0 || i % 15 == 0) {
                    wait();
                    // condition to fix the code
                    if (i > n) break;
                }
                if (i > n) break;
                printBuzz.run();
                i++;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 15 != 0) {
                    wait();
                    // condition to fix the code
                    if (i > n) break;
                }
                if (i > n) break;
                printFizzBuzz.run();
                i++;
                notifyAll();
            }
        }
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (i <= n) {
                while (i % 3 == 0 || i % 5 == 0) {
                    wait();
                    if (i > n) break;
                }
                if (i > n) break;
                printNumber.accept(i);
                i++;
                notifyAll();
            }
        }
    }

}
