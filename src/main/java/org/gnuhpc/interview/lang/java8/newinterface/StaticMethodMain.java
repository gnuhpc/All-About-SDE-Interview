package org.gnuhpc.interview.lang.java8.newinterface;

/**
 * Created by gnuhpc on 2017/1/11.
 */
public class StaticMethodMain implements StaticMethodI {
    public boolean isNull(String str) {
        System.out.println("Impl Null Check");

        return str == null ? true : false;
    }

    public static void main(String[] args) {
        StaticMethodMain main = new StaticMethodMain();
        main.print("Hello world");
    }
}
