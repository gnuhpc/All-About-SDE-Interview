package org.gnuhpc.interview.concurrency.producerconsumer.rawpattern.allinqueue;

import java.util.Random;

public class EaterThread extends Thread {
    private final Random random;
    private final Table table;

    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            while (true) {
                String cake = table.take();
                eating(cake);
            }
        } catch (InterruptedException e) {
        }
    }

    private void eating(String cake) throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
        System.out.println("Eatten cake: " + cake);
    }
}
