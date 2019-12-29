package org.gnuhpc.bigdata.concurrency.terminate.simple;


public class Main {
    public static void main(String[] args) {
        // 执行Host的繁重处理的线程
        // 启动
        Host host = new Host();
        host.start();

        // 休眠约15秒
        try {
            Thread.sleep(15000);
        }
        catch (InterruptedException e) {
        }

        // 取消
        System.out.println("***** interrupt *****");
        host.interrupt();
    }
}
