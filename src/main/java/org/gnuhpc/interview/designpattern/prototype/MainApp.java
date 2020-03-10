package org.gnuhpc.interview.designpattern.prototype;

/*
这也是一个创建型设计模式。在构造cost特别大的时候使用。
首先定义一个接口（extends Cloneable ） 表示可以clone。注意是浅拷贝
然后创建实体类实现这个接口，具体实现中clone接口调用clone方法。 最后创建一个Clone工厂封装一下这个接口的实体类。

在使用的时候先创建一个工厂类，然后创建实体类，最后将这个实体类传入工厂类，调用工厂类中的获取实体拷贝的方法。

 */
public class MainApp {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Handles routing makeCopy method calls to the
        // right subclasses of Animal

        CloneFactory animalMaker = new CloneFactory();

        // Creates a new Sheep instance

        Sheep sally = new Sheep();

        // Creates a clone of Sally and stores it in its own
        // memory location

        Sheep clonedSheep = (Sheep) animalMaker.getClone(sally);

        // These are exact copies of each other

        System.out.println(sally);

        System.out.println(clonedSheep);

        System.out.println("Sally HashCode: " + System.identityHashCode(System.identityHashCode(sally)));

        System.out.println("Clone HashCode: " + System.identityHashCode(System.identityHashCode(clonedSheep)));
    }

}