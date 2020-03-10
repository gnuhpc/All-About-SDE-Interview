package org.gnuhpc.interview.designpattern.bridge.concreteimplementor;

import org.gnuhpc.interview.designpattern.bridge.implementor.Color;

public class Green implements Color {

    @Override
    public void applyColor() {
        System.out.println("Applying green color");
    }

}
