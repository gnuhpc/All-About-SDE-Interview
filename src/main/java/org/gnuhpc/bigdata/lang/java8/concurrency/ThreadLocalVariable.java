package org.gnuhpc.bigdata.lang.java8.concurrency;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by huangpengcheng on 2017/1/5 0005.
 */
public class ThreadLocalVariable {
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.<SimpleDateFormat>withInitial
            (() -> {return new SimpleDateFormat("yyyyMMdd HHmm");});
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd HHmm");

    public static void main(String[] args) throws InterruptedException {
        Runnable runThread= ()->{
            System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
            System.out.println("Non Local Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter2.toPattern());
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            formatter.set(new SimpleDateFormat());
            formatter2=new SimpleDateFormat();

            System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
            System.out.println("Non Local Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter2.toPattern());

        };

        Thread thread;
        for(int i=0; i<100;i++){
            thread = new Thread(runThread,String.valueOf(i));
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }
}
