package org.gnuhpc.bigdata.designpattern.strategy;

import org.gnuhpc.bigdata.designpattern.strategy.concretestrategy.ItFlys;
import org.gnuhpc.bigdata.designpattern.strategy.context.Animal;
import org.gnuhpc.bigdata.designpattern.strategy.concretecontext.Bird;
import org.gnuhpc.bigdata.designpattern.strategy.concretecontext.Dog;

//策略模式，可以将策略(strategy)作为一个基类的成员动态传入，用于将能力动态传入的场景，也可以用来减少if语句
//而这个成员则是一个接口，多个实体类可以实现此类，进行具体实现能力
//对于基类(context)的继承类，继承了基类的能力，且动态传入了能力类进行能力的实现
// 一方面能利用继承将共同的能力继承下来，一方面利用组合将能力动态取得。
public class MainApp {

    public static void main(String[] args) {

        Animal sparky = new Dog();
        Animal tweety = new Bird();

        System.out.println("Dog: " + sparky.tryToFly());
        System.out.println("Bird: " + tweety.tryToFly());

        // This allows dynamic changes for flyingType

        sparky.setFlyingAbility(new ItFlys());

        System.out.println("Dog: " + sparky.tryToFly());

    }

}