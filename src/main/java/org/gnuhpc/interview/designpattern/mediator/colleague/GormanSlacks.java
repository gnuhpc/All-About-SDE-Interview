package org.gnuhpc.interview.designpattern.mediator.colleague;

import org.gnuhpc.interview.designpattern.mediator.abstractcolleague.Colleague;
import org.gnuhpc.interview.designpattern.mediator.abstractmediator.Mediator;

public class GormanSlacks extends Colleague {
    public GormanSlacks(Mediator newMediator) {
        super(newMediator);
        System.out.println("Gorman Slacks signed up with the stockexchange\n");
    }
}
