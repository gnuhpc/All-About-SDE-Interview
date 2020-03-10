package org.gnuhpc.interview.lang.java8.newinterface;

/**
 * Created by gnuhpc on 2017/1/11.
 */
public interface StaticMethodI {
    default void print(String str) {
        if (!isNull(str))
            System.out.println("MyData Print::" + str);
    }

    static boolean isNull(String str) {
        System.out.println("Interface Null Check");

        return str == null ? true : "".equals(str) ? true : false;
    }
}
