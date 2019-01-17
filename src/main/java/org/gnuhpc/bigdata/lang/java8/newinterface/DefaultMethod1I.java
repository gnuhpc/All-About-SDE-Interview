package org.gnuhpc.bigdata.lang.java8.newinterface;

/**
 * Created by gnuhpc on 2017/1/11.
 */
public interface DefaultMethod1I {
    void method1(String str);

    default void print(String str){
        System.out.println("Default Print1:" + str);
    }
}
