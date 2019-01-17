package org.gnuhpc.bigdata.designpattern.bridge.refinedabstraction;

import org.gnuhpc.bigdata.designpattern.bridge.abstraction.Shape;
import org.gnuhpc.bigdata.designpattern.bridge.implementor.Color;

public class Square extends Shape {

	public Square(Color color) {
		super(color);
	}
	
	@Override
	public void printColor() {
		color.applyColor();
	}

}
