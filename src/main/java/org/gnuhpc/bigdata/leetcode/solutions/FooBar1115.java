package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class FooBar1115 {
    private int     n;
    private boolean fooOrBar;

    public FooBar1115(int n) {
        this.n = n;
        this.fooOrBar = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                while (!fooOrBar) {
                    this.wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fooOrBar = !fooOrBar;
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                while (fooOrBar) {
                    this.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooOrBar = !fooOrBar;
                this.notifyAll();
            }
        }
    }
}
