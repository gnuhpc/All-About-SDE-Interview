package org.gnuhpc.interview.concurrency.readwritelock.impl;

public final class ReadWriteLock {
    private int readingReaders = 0; // (A)…实际正在读取中的线程个数
    private int waitingWriters = 0; // (B)…正在等待写入的线程个数
    private int writingWriters = 0; // (C)…实际正在写入中的线程个数
    private boolean preferWriter = true; // 若写入优先，则为true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;                       // (A) 实际正在读取的线程个数加1
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (A) 实际正在读取的线程个数减1
        preferWriter = true; //读操作后优先进行写
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;                       // (B) 正在等待写入的线程个数加1
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;                   // (B) 正在等待写入的线程个数减1
        }
        writingWriters++;                       // (C) 实际正在写入的线程个数加1
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (C) 实际正在写入的线程个数减1
        preferWriter = false;//写操作后优先进行读操作
        notifyAll();
    }
}
