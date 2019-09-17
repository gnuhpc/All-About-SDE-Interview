package org.gnuhpc.bigdata.designpattern.singleton.stat;

/*
使用静态内部类的方法，这种方法也是《Effective Java》上所推荐的。
这种写法仍然使用JVM本身机制保证了线程安全问题；
由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；
同时读取实例的时候不会进行同步，没有性能缺陷；
也不依赖 JDK 版本。

lazy initialization
 */

public class InnerStaticSingleton {
    private static class SingletonHolder {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    private InnerStaticSingleton(){}
    public static final InnerStaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
