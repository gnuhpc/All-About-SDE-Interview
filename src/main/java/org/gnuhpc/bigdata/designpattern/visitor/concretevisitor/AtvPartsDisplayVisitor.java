package org.gnuhpc.bigdata.designpattern.visitor.concretevisitor;

import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Fender;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Oil;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.PartsOrder;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Wheel;
import org.gnuhpc.bigdata.designpattern.visitor.visitor.AtvPartVisitor;

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
