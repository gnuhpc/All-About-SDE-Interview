package org.gnuhpc.bigdata.designpattern.visitor.concreteelement;

import org.gnuhpc.bigdata.designpattern.visitor.element.AtvPart;
import org.gnuhpc.bigdata.designpattern.visitor.visitor.AtvPartVisitor;

public class Fender implements AtvPart {
	@Override
	public void accept(AtvPartVisitor visitor) {
		visitor.visit(this);
	}
}
