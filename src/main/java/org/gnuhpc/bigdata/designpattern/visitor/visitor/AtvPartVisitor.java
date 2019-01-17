package org.gnuhpc.bigdata.designpattern.visitor.visitor;

import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Fender;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Oil;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.PartsOrder;
import org.gnuhpc.bigdata.designpattern.visitor.concreteelement.Wheel;

public interface AtvPartVisitor {
	void visit(Wheel wheel);
	void visit(Fender fender);
	void visit(Oil oil);
	void visit(PartsOrder partsOrder);
}
