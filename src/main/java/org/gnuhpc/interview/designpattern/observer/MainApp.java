package org.gnuhpc.interview.designpattern.observer;

import org.gnuhpc.interview.designpattern.observer.concreteobserver.DisplayBoard;
import org.gnuhpc.interview.designpattern.observer.concreteobserver.Mobile;
import org.gnuhpc.interview.designpattern.observer.concreteobserverable.Apple;
import org.gnuhpc.interview.designpattern.observer.concreteobserverable.IBM;
import org.gnuhpc.interview.designpattern.observer.concreteobserverable.Microsoft;

/**
 * Design Pattern series by http://java9s.com
 *
 * @author java9s.com
 * 用于一对多个类之间进行通知使用
 * Observable类作为消息的发布者.
 * Observer类作为消息的接收者.
 * 上述两个都是接口，JDK中有专门的接口类。
 * 发布者分为抽象发布者（Stock）和具体发布者（Apple、IBM...）
 * 抽象发布者内部维护了一个接收者的队列，并提供注册和注销与通知的功能另外还有发布消息的接口。
 * 另外包括更新功能对这个队列进行遍历然后调用Observer的一个必须实现的update方法，这个方法决定了Observer是如何对这个消息进行处理的。
 * 具体发布者可以添加一些每个发布者不一样的信息，比如此案例中的股票名称
 * Observable也可以放在一个线程类中不断更新，这样就可以持续发消息了。
 */
public class MainApp {
    public static void main(String[] args) {
        //Create Observable
        IBM ibm = new IBM();
        Microsoft microsoft = new Microsoft();
        Apple apple = new Apple();

        //Create Observers
        Mobile mobile = new Mobile();
        DisplayBoard displayBoard = new DisplayBoard();

        //Register Observers for Observable
        ibm.registerObserver(mobile);
        ibm.registerObserver(displayBoard);

        microsoft.registerObserver(mobile);
        microsoft.registerObserver(displayBoard);

        apple.registerObserver(mobile);
        apple.registerObserver(displayBoard);

        //Update Observable
        for (int i = 0; i < 10; i++) {
            ibm.updateStockPrice((int) (Math.random() * 101) + 1);
            microsoft.updateStockPrice((int) (Math.random() * 101) + 1);
            apple.updateStockPrice((int) (Math.random() * 101) + 1);
        }

    }
}
