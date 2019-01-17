package org.gnuhpc.bigdata.designpattern.bridge.refinedabstraction;

import org.gnuhpc.bigdata.designpattern.bridge.abstraction.Shape;
import org.gnuhpc.bigdata.designpattern.bridge.implementor.Color;

public class Circle extends Shape {

	public Circle(Color color) {
		super(color);
	}
	
	@Override
	public void printColor() {
		color.applyColor();
	}

}
