package org.gnuhpc.interview.designpattern.bridge.abstraction;

import org.gnuhpc.interview.designpattern.bridge.implementor.Color;

public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract public void printColor();
}
