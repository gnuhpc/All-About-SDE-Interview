package org.gnuhpc.bigdata.designpattern.bridge;

import org.gnuhpc.bigdata.designpattern.bridge.abstraction.Shape;
import org.gnuhpc.bigdata.designpattern.bridge.concreteimplementor.Blue;
import org.gnuhpc.bigdata.designpattern.bridge.concreteimplementor.Green;
import org.gnuhpc.bigdata.designpattern.bridge.concreteimplementor.Red;
import org.gnuhpc.bigdata.designpattern.bridge.implementor.Color;
import org.gnuhpc.bigdata.designpattern.bridge.refinedabstraction.Circle;
import org.gnuhpc.bigdata.designpattern.bridge.refinedabstraction.Square;

/*
当一组类和另一组类需要进行组合时，并且不确定有多少种组合时采用该模式。使用组合进行了桥接！
具体应用上，某个实体类的属性很多需要组合设计的时候很有用

具体的，首先设计主抽象类（或者接口）和其子类。然后再设计另一个主抽象类（必须是抽象类），留出传入第一个主抽象类（作为Bridge）的方法以及需要子类具体实现的方法。
随后创建第二个主抽象类的子类，用传入的第一个主抽象类的对象实现抽象方法。

在使用的时候，首先实例化第一个主抽象类的具体子类对象，随后传入第二个主抽象类的具体子类，然后调用相关方法即可。

 */
public class MainApp{

	public static void main(String[] args) {
		Color blue = new Blue();
		Shape blueSquare = new Square(blue);
		
		Color red = new Red();
		Shape redCircle = new Circle(red);
		
		Color green = new Green();
		Shape greenCircle = new Circle(green);
		Shape greenSquare = new Square(green);
		
		blueSquare.printColor();
		redCircle.printColor();
		greenCircle.printColor();
		greenSquare.printColor();
	}

}
