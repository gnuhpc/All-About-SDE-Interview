package org.gnuhpc.bigdata.concurrency.deadlock;

/**
 * Created by jsssn on 07-May-17.
 */
public class Sample {

    private Object key1 = new Object();
    private Object key2 = new Object();

    public void a() {
        synchronized (key1) {
            System.out.println("[" + Thread.currentThread().getName() + "] I am in a()");
            b();
        }
    }

    public void b() {
        synchronized (key2) {
            System.out.println("[" + Thread.currentThread().getName() + "] I am in b()");
            a();
        }
    }
}
