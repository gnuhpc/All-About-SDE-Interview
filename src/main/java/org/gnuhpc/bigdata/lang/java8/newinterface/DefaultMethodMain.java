package org.gnuhpc.bigdata.lang.java8.newinterface;

/**
 * Created by gnuhpc on 2017/1/11.
 */
public class DefaultMethodMain implements DefaultMethod1I, DefaultMethod2I {

    public static void main(String[] args) {
        DefaultMethodMain main = new DefaultMethodMain();
        main.print("Hello world");
    }

    @Override
    public void method1(String str) {
        System.out.println("Method1:"+ str);
    }

    @Override
    public void print(String str) {
        System.out.println("org.gnuhpc.bigdata.concurrency.future.impl.Main print:" + str);
    }
}
