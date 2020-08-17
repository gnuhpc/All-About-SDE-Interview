package org.gnuhpc.interview.concurrency.alternatively;

/**
 * Copyright gnuhpc 2020/8/9
 */

public class OddEven{
    public static void main(String[] args) {
        // shared class object
        SharedPrinter sp = new SharedPrinter();
        // creating two threads
        Thread t1 = new Thread(new EvenNumProducer(sp, 10));
        Thread t2 = new Thread(new OddNumProducer(sp, 10));
        // starting threads
        t1.start();
        t2.start();
    }
}
// Shared class used by both threads
class SharedPrinter{
    boolean evenFlag = false;

    //Method for printing even numbers
    public void printEvenNum(int num){
        synchronized (this) {
            // While condition as mandated to avoid spurious wakeup
            while(!evenFlag){
                try {
                    //asking current thread to give up lock
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Even: " + num);
            evenFlag = false;
            // Wake up thread waiting on this monitor(lock)
            notifyAll();
        }
    }

    //Method for printing odd numbers
    public void printOddNum(int num){
        synchronized (this) {
            // While condition as mandated to avoid spurious wakeup
            while(evenFlag){
                try {
                    //asking current thread to give up lock
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Odd: " + num);
            evenFlag = true;
            // Wake up thread waiting on this monitor(lock)
            notifyAll();
        }
    }
}

// Thread Class generating Even numbers
class EvenNumProducer implements Runnable{
    SharedPrinter sp;
    int limit;
    EvenNumProducer(SharedPrinter sp, int limit){
        this.sp = sp;
        this.limit = limit;
    }
    @Override
    public void run() {
        for(int i = 2; i <= limit; i = i+2){
            sp.printEvenNum(i);
        }
    }
}

//Thread Class generating Odd numbers
class OddNumProducer implements Runnable{
    SharedPrinter sp;
    int limit;
    OddNumProducer(SharedPrinter sp, int limit){
        this.sp = sp;
        this.limit = limit;
    }
    @Override
    public void run() {
        for(int i = 1; i <= limit; i = i+2){
            sp.printOddNum(i);
        }
    }
}