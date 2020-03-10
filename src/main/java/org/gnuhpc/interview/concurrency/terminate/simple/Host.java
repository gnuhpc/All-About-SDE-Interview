package org.gnuhpc.interview.concurrency.terminate.simple;

public class Host extends Thread {
    @Override
    public void run() {
        try {
            execute(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            if (isInterrupted()) {
                throw new InterruptedException();
            }
            doHeavyJob();
        }
    }

    private void doHeavyJob() {
        // 下面代码用于表示"无法取消的繁重处理"（循环处理约10秒）
        System.out.println("doHeavyJob BEGIN");
        long start = System.currentTimeMillis();
        while (start + 10000 > System.currentTimeMillis()) {
            // busy loop
        }
        System.out.println("doHeavyJob END");
    }

}
