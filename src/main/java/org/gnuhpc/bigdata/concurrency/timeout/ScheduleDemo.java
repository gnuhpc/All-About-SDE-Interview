package org.gnuhpc.bigdata.concurrency.timeout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleDemo {
    static ScheduledExecutorService executorService= Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()*2);

    public static void main(String[] args) {
        RunningTask runningTask=new RunningTask();
        ScheduledFuture<?> scheduledFuture = executorService.schedule(
                new CancelTask(runningTask), 3, TimeUnit.SECONDS);
        runningTask.doing();
        if(!scheduledFuture.isDone()){
            scheduledFuture.cancel(true);
        }
    }

    static class RunningTask  {

        private volatile boolean isStop;

        public void stop(){
            this.isStop=true;
        }

        public void doing() {
            while (!isStop){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
            System.out.println("任务被中断。");
        }
    }

    static class CancelTask implements Runnable {

        private RunningTask runningTask;

        public CancelTask(RunningTask runningTask) {
            this.runningTask = runningTask;
        }

        @Override
        public void run() {
            runningTask.stop();
        }
    }
}
