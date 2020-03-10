package org.gnuhpc.interview.designpattern.singleton.stat;

//静态变量化
public class StaticSingleton {
    private static StaticSingleton uniqueInstance = new StaticSingleton();

    private StaticSingleton() {
    }

    public static StaticSingleton getInstance() {
        return uniqueInstance;
    }
}
