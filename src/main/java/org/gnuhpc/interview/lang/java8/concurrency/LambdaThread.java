package org.gnuhpc.interview.lang.java8.concurrency;

/**
 * Created by gnuhpc on 2017/1/5.
 */
public class LambdaThread {
    public static void main(String[] args) {
        Runnable multiStateMent = () -> {
            System.out.println("Hello World!");
            Runnable nestedTherad = () -> {
                System.out.println("Have a Sleep 5");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sleep stop");
            };
            nestedTherad.run();
        };

        multiStateMent.run();
    }
}
