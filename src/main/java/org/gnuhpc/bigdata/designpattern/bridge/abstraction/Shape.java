package org.gnuhpc.bigdata.designpattern.bridge.abstraction;

import org.gnuhpc.bigdata.designpattern.bridge.implementor.Color;

public abstract class Shape {

	protected Color color;
	
	public Shape(Color color) {
		this.color = color;
	}
	
	abstract public void printColor();
}
