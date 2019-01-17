package org.gnuhpc.bigdata.designpattern.strategy.concretestrategy;

import org.gnuhpc.bigdata.designpattern.strategy.strategy.Flys;

// Class used if the Animal can fly
public class ItFlys implements Flys {
    public String fly() {
        return "Flying High";
    }
}

