package org.gnuhpc.bigdata.concurrency.basic;

import lombok.AllArgsConstructor;

// synchronized 可以用在任何Java Object上
// 用在static上则是在class，在一般对象上就是instance级别的，用在一般方法上就是method同步
@AllArgsConstructor
class Bank {
    private int money;
    private String name;

    //Equals to
    //  deposit(int m){
    //      sychronized(this){}
    //  }
    //
    public synchronized void deposit(int m) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("Sleep ending, deposit 100!");
        money += m;
    }

    public synchronized boolean withdraw(int m){
        if(money>=m){
            money-=m;
            return true;
        } else {
            return false;
        }
    }

    //synchronized static method works almost the same way as the synchronized method but with different lock object java.lang.class
    public static synchronized void query(String msg) throws InterruptedException {

        System.out.println("This is " + msg);
        Thread.sleep(10000);
        System.out.println();
    }
}

@AllArgsConstructor
class CustomerA implements Runnable{
    private Bank bank;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId()+":Trying to withdraw 100");
        if(!Thread.holdsLock(bank)){
            System.out.println("I have to wait for the synchronized lock ");
        }
        if(bank.withdraw(100)){
            System.out.println(Thread.currentThread().getId()+":Withdraw Success!");
        } else{
            System.out.println(Thread.currentThread().getId()+":Withdraw Failed!");
        }
    }
}

@AllArgsConstructor
class CustomerB implements Runnable{
    private Bank bank;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId()+":Trying to deposit 100,But I will sleep 10s first!");
        try {
            //当一个线程正在运行deposit方法时，其他线程就无法运行deposit方法或withdraw方法等该实例的synchronized方法。
            //Every instance owns a independent lock for synchronized methods.
            //Thread.sleep won't put this thread into the waiting queue.
            Thread.sleep(10000);
            bank.deposit(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@AllArgsConstructor
class CustomerC implements Runnable{
    private String msg;
    @Override
    public void run() {
        try {
            Bank.query(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank(100,"gnuhpc");
        new Thread(new CustomerB(bank)).start();
        Thread.sleep(2000);
        new Thread(new CustomerA(bank)).start();
        new Thread(new CustomerA(bank)).start();

        Thread.sleep(10000);
        new Thread(new CustomerC("Bob")).start();
        Thread.sleep(2000);
        new Thread(new CustomerC("John")).start();
    }
}
