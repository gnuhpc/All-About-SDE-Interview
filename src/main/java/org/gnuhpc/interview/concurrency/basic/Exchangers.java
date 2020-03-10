package org.gnuhpc.interview.concurrency.basic;

import java.util.concurrent.Exchanger;

/**
 * With the help of Exchanger -> two threads can exchange objects
 * <p>
 * exchange() -> exchanging objects is done via one of the two exchange()
 * methods
 * <p>
 * For example: genetic algorithms, training neural networks
 */

class First1Worker implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public First1Worker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true) {

            counter = counter + 1;
            System.out.println("FirstWorker incremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Second2Worker implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public Second2Worker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true) {

            counter = counter - 1;
            System.out.println("SecondWorker decremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Exchangers {
    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new First1Worker(exchanger)).start();
        new Thread(new Second2Worker(exchanger)).start();

    }
}