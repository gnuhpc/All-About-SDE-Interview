package org.gnuhpc.interview.designpattern.visitor;

import org.gnuhpc.interview.designpattern.visitor.concreteelement.Fender;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Oil;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.PartsOrder;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Wheel;
import org.gnuhpc.interview.designpattern.visitor.concretevisitor.AtvPartsDisplayVisitor;
import org.gnuhpc.interview.designpattern.visitor.concretevisitor.AtvPartsShippingVisitor;

/*
一种将算法与对象结构分离的软件设计模式。
首先有一个实体类接口Element，其中有一个accept方法接受一个Visitor算法接口的变量。
随后所有的实体类实现这个接口。实现accept方法统一都是return visitor.visit(this);
随后写算法接口Visitor，其中根据每一个实体类都有一个visit方法。
随后具体的算法类实现这个接口。

在Client使用中，使用实体类.accept(visitor)进行计算。

 */
public class MainApp {

    public static void main(String[] args) {
        PartsOrder order = new PartsOrder();
        order.addPart(new Wheel());
        order.addPart(new Fender());
        order.addPart(new Oil());

        order.accept(new AtvPartsShippingVisitor());
        order.accept(new AtvPartsDisplayVisitor());
    }
}
