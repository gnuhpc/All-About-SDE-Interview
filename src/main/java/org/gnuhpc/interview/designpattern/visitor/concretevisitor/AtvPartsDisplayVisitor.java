package org.gnuhpc.interview.designpattern.visitor.concretevisitor;

import org.gnuhpc.interview.designpattern.visitor.concreteelement.Fender;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Oil;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.PartsOrder;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Wheel;
import org.gnuhpc.interview.designpattern.visitor.visitor.AtvPartVisitor;

public class AtvPartsDisplayVisitor implements AtvPartVisitor {

    @Override
    public void visit(Wheel wheel) {
        System.out.println("We have a wheel.");
    }

    @Override
    public void visit(Fender fender) {
        System.out.println("We have a fender.");

    }

    @Override
    public void visit(Oil oil) {
        System.out.println("We have oil.");

    }

    @Override
    public void visit(PartsOrder partsOrder) {
        System.out.println("We have an order.");

    }

}
