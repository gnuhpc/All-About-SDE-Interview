package org.gnuhpc.interview.concurrency.terminate.twoparse;

public class CountupThread extends GracefulThread {
    // 计数值
    private long counter = 0;

    // 操作
    @Override
    protected void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        Thread.sleep(500);
    }

    // 终止请求
    @Override
    protected void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
