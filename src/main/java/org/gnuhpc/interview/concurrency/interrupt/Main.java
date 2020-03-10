package org.gnuhpc.interview.concurrency.interrupt;

/*
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false
*State3: false
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false
.State2: false

 */
public class Main {
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (Thread.currentThread().isInterrupted()) {//永远进不去
                            System.out.println("State1: " + Thread.currentThread().isInterrupted());
                            throw new InterruptedException();
                        }
                        System.out.print(".");
                        System.out.println("State2: " + Thread.currentThread().isInterrupted());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {//注意在这里线程的标志位已经被置为false了
                        System.out.print("*");
                        System.out.println("State3: " + Thread.currentThread().isInterrupted());
                    }
                }
            }
        };

        // 启动线程
        thread.start();

        // 等待5秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        // 只interrupt线程一次
        thread.interrupt();
    }
}
