package org.gnuhpc.bigdata.designpattern.template;

import org.gnuhpc.bigdata.designpattern.template.abstractbase.OrderTemplate;
import org.gnuhpc.bigdata.designpattern.template.concretetemplate.StoreOrder;
import org.gnuhpc.bigdata.designpattern.template.concretetemplate.WebOrder;
/*
模板方法指的是一个抽象类中的一个具体实现方法的流程是一样的，
但可变步骤是由抽象方法组成的，这样子类继承抽象类后通过实现这些抽象方法来达成不同的实现。
相当于加hook
 */
public class MainApp {

	public static void main(String[] args) {
		System.out.println("Web Order:");
		
		OrderTemplate webOrder = new WebOrder();
		webOrder.processOrder();
		
		System.out.println("\nStore Order:");
		
		OrderTemplate storeOrder = new StoreOrder();
		storeOrder.processOrder();
	}
}
