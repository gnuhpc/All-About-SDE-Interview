package org.gnuhpc.interview.designpattern.mediator.colleague;

import org.gnuhpc.interview.designpattern.mediator.abstractcolleague.Colleague;
import org.gnuhpc.interview.designpattern.mediator.abstractmediator.Mediator;

public class JTPoorman extends Colleague {
    public JTPoorman(Mediator newMediator) {
        super(newMediator);
        System.out.println("JT Poorman signed up with the stockexchange\n");
    }
}
