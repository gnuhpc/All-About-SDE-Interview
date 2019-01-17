package org.gnuhpc.bigdata.designpattern.bridge.concreteimplementor;

import org.gnuhpc.bigdata.designpattern.bridge.implementor.Color;

public class Red implements Color {

	@Override
	public void applyColor() {
		System.out.println("Applying red color");
	}

}
