package org.gnuhpc.interview.designpattern.decorator;

import org.gnuhpc.interview.designpattern.decorator.concretedecorator.Mozzarella;
import org.gnuhpc.interview.designpattern.decorator.concretedecorator.TomatoSauce;
import org.gnuhpc.interview.designpattern.decorator.component.Pizza;
import org.gnuhpc.interview.designpattern.decorator.concretecomponent.PlainPizza;

/*用于对一个实体类进行不断增强的模式。

首先，定义一个包含所有实体类都要实现的方法的接口。(Pizza)
然后，定义一个装饰者抽象类(Decorator)来实现这个接口（因为要考虑到它会成为一个变量传入另一个类中），以has a 的方式包含这个接口的一个句柄，从构造函数传入符合这个接口的变量，具体实现就拿这个传入的变量进行。
接着，定义一个核心类也实现第一步定义的接口(PlainPizza)，这个类就是所有装饰者要装饰的那个类。
然后，实现具体装饰类(Mozzarella)。

调用的时候采用套用调用的方式：
Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));

这样在执行时就会从核心类的这个方法开始执行。

在实际的JDK中，I/O包是一个应用

*/
public class MainApp {
    public static void main(String[] args) {
        // The PlainPizza object is sent to the Mozzarella constructor
        // and then to the TomatoSauce constructor

        Pizza pizza = new TomatoSauce(new Mozzarella(new PlainPizza()));
        System.out.println("Ingredients: " + pizza.getDescription());
        System.out.println("Price: " + pizza.getCost());
    }

}