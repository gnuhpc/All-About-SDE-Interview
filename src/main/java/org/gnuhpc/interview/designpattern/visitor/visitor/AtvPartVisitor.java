package org.gnuhpc.interview.designpattern.visitor.visitor;

import org.gnuhpc.interview.designpattern.visitor.concreteelement.Fender;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Oil;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.PartsOrder;
import org.gnuhpc.interview.designpattern.visitor.concreteelement.Wheel;

public interface AtvPartVisitor {
    void visit(Wheel wheel);

    void visit(Fender fender);

    void visit(Oil oil);

    void visit(PartsOrder partsOrder);
}
