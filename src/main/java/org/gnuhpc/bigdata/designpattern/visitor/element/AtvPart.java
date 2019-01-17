package org.gnuhpc.bigdata.designpattern.visitor.element;

import org.gnuhpc.bigdata.designpattern.visitor.visitor.AtvPartVisitor;

public interface AtvPart {
	public void accept(AtvPartVisitor visitor);
}
