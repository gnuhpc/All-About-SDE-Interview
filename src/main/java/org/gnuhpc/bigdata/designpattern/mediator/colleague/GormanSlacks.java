package org.gnuhpc.bigdata.designpattern.mediator.colleague;

import org.gnuhpc.bigdata.designpattern.mediator.abstractcolleague.Colleague;
import org.gnuhpc.bigdata.designpattern.mediator.abstractmediator.Mediator;

public class GormanSlacks extends Colleague {
    public GormanSlacks(Mediator newMediator) {
        super(newMediator);
        System.out.println("Gorman Slacks signed up with the stockexchange\n");
    }
}
