package org.gnuhpc.interview.designpattern.bridge.refinedabstraction;

import org.gnuhpc.interview.designpattern.bridge.abstraction.Shape;
import org.gnuhpc.interview.designpattern.bridge.implementor.Color;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void printColor() {
        color.applyColor();
    }

}
