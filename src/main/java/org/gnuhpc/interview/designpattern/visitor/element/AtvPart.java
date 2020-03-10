package org.gnuhpc.interview.designpattern.visitor.element;

import org.gnuhpc.interview.designpattern.visitor.visitor.AtvPartVisitor;

public interface AtvPart {
    public void accept(AtvPartVisitor visitor);
}
