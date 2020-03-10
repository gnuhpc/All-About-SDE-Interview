package org.gnuhpc.interview.concurrency.producerconsumer.rawpattern.allinqueue;

public class Table {//所有同步方法都封装在这边，客户端使用调用即可，比allinclient更好
    private final String[] buffer;
    private int tail;  // 下次put的位置
    private int head;  // 下次take的位置, 即下次取蛋糕的位置
    private int count; // buffer中的蛋糕个数

    public Table(int count) {
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buffer.length) {
            System.out.println(Thread.currentThread().getName() + " wait BEGIN");
            wait();
            System.out.println(Thread.currentThread().getName() + " wait END");
        }
        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();//放在前边也行，因为notify并不释放sync锁，
        // 只有等到sync块执行完毕后才会真正释放，
        // 但是放在后边比较清晰
    }

    public synchronized String take() throws InterruptedException {
        while (count <= 0) {
            System.out.println(Thread.currentThread().getName() + " wait BEGIN");
            wait();
            System.out.println(Thread.currentThread().getName() + " wait END");
        }
        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }
}
