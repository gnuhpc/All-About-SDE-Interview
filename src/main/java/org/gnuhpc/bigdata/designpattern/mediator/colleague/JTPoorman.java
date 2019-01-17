package org.gnuhpc.bigdata.designpattern.mediator.colleague;

import org.gnuhpc.bigdata.designpattern.mediator.abstractcolleague.Colleague;
import org.gnuhpc.bigdata.designpattern.mediator.abstractmediator.Mediator;

public class JTPoorman extends Colleague {
    public JTPoorman(Mediator newMediator) {
        super(newMediator);
        System.out.println("JT Poorman signed up with the stockexchange\n");
    }
}
