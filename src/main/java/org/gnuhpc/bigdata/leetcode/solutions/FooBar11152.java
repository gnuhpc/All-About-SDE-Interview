package org.gnuhpc.bigdata.leetcode.solutions;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.sql.Time;
import java.util.concurrent.Semaphore;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class FooBar11152 {
    private int       n;
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public FooBar11152(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        Thread.sleep(10);
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
