package org.gnuhpc.bigdata.designpattern.flyweight;

import org.gnuhpc.bigdata.designpattern.flyweight.facade.InventorySystem;

/*
该模式在需要共享一些对象的系统中使用，比如围棋中的黑白棋子，都是一样的，
因此在一个围棋系统中只需要有一个黑棋子一个白棋子对象。如果有不同的属性，比如坐标，那么通过外部转入。

首先要创建一个抽象享元类定义了动作，然后实现多个具体的类。
最后创建一个工厂类，里面有一个HashTable内部变量。可以在创建工厂类的时候就构建好所有可能重复使用的类的实例，也可以在getInstance的时候通过判断进行创建。
在创建具体类的时候通过工厂类进行，如果需要外部状态则使用方法调用临时传入，注意不要存储，因为是共享的对象。
 */
public class MainApp{

	public static void main(String[] args) {
		InventorySystem ims = new InventorySystem();

		ims.takeOrder("Roomba", 221);
		ims.takeOrder("Bose Headphones", 361);
		ims.takeOrder("Samsung TV", 432);
		ims.takeOrder("Samsung TV", 323);
		ims.takeOrder("Roomba", 563);
		ims.takeOrder("Bose Headphones", 321);
		ims.takeOrder("Roomba", 234);
		ims.takeOrder("Samsung TV", 54);
		ims.takeOrder("Roomba", 34);
		ims.takeOrder("Bose Headphones", 365);
		ims.takeOrder("Samsung TV", 332);
		ims.takeOrder("Roomba", 456);

		ims.process();
		
		System.out.println(ims.report());

	}
}
