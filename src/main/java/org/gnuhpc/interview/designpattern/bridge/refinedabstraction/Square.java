package org.gnuhpc.interview.designpattern.bridge.refinedabstraction;

import org.gnuhpc.interview.designpattern.bridge.abstraction.Shape;
import org.gnuhpc.interview.designpattern.bridge.implementor.Color;

public class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void printColor() {
        color.applyColor();
    }

}
