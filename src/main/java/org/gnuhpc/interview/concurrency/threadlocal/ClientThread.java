package org.gnuhpc.interview.concurrency.threadlocal;

public class ClientThread extends Thread {
    public ClientThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println(getName() + " BEGIN");
        for (int i = 0; i < 10; i++) {
            Log.println("i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        // 不再需要Log.close() ，是因为有一个watch线程去关闭
        System.out.println(getName() + " END");
    }
}
