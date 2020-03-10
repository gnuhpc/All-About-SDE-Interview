package org.gnuhpc.interview.concurrency.singlethreadedexecution;

import java.util.Hashtable;
import java.util.Map;

public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";
    //Hashtable和ConcurrentHashMap都是线程安全的，前者使用的就是SingleThreadedExecution模式，而后者则是将内部数据结构分段加synchronized
    //因此前者更容易发生线程冲突。
    private Hashtable<String, String> pair = new Hashtable<>();

    //线程要执行synchronized的实例方法必须获取this锁，而能够获取一个实例的锁的线程只能有一个。因此才会有唯一性的效果。实例不同，锁也不同。
    //long和double的赋值在Java规范上并不一定是原子操作。可以声明为volatile，亦或者使用java.util.concurrent.atomic包中的原子类
    public synchronized void pass(String name, String address) throws InterruptedException {
        this.counter++;
        this.name = name;
        //延长临界区提高检查错误的可能性
        Thread.sleep(1000);
        this.address = address;
        check();
    }

    public void passHash(String name, String address) {
        pair.put(name, address);
        checkHash();
    }

    private void checkHash() {
        for (Map.Entry<String, String> e : pair.entrySet()) {
            if (e.getKey().charAt(0) != e.getValue().charAt(0)) {
                System.out.println("***** BROKEN ***** " + toString());
            }
        }
    }

    public synchronized String toString() {
        return "No." + counter + ": " + name + ", " + address;
    }

    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("***** BROKEN ***** " + toString());
        }
    }
}
