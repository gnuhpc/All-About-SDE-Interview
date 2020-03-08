package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.concurrent.Semaphore;

/**
 * Copyright gnuhpc 2019/10/31
 */
public class H2O1117 {
    private volatile int       hCount = 0;
    private          Semaphore sh     = new Semaphore(2);
    private          Semaphore so     = new Semaphore(1);

    public H2O1117() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        sh.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (++hCount % 2 == 0) {
            so.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        so.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        sh.release(2);
    }
}
