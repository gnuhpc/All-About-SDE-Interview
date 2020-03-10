package org.gnuhpc.interview.designpattern.visitor.concreteelement;

import org.gnuhpc.interview.designpattern.visitor.element.AtvPart;
import org.gnuhpc.interview.designpattern.visitor.visitor.AtvPartVisitor;

public class Wheel implements AtvPart {
    @Override
    public void accept(AtvPartVisitor visitor) {
        visitor.visit(this);
    }
}
