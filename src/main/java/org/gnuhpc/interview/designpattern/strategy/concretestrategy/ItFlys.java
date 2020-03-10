package org.gnuhpc.interview.designpattern.strategy.concretestrategy;

import org.gnuhpc.interview.designpattern.strategy.strategy.Flys;

// Class used if the Animal can fly
public class ItFlys implements Flys {
    public String fly() {
        return "Flying High";
    }
}

