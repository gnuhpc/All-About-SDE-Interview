package org.gnuhpc.bigdata.concurrency.basic;

import lombok.AllArgsConstructor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//方法1 继承Thread
@AllArgsConstructor
class PrintThread extends Thread {
    private String message;

    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(message+i);
        }
    }
}

//方法2 实现Runnable , 运行的时候new Thread放进去， 这种比较灵活 , preferable
@AllArgsConstructor
class Printer implements Runnable{
    private String message;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(message + i);
        }
    }
}

public class ThreadDemo {

    public static void main(String[] args) {
        //启动方法一： new start
        new PrintThread("Good!").start();
        new Thread(new Printer("Excellent")).start();

        Runnable thread1 = () ->{
            System.out.println("Hello world!");
        };



        //启动方法二：Factory-> newThread -> start
        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(new Printer("Go!")).start();
        factory.newThread(thread1).start();
    }
}
