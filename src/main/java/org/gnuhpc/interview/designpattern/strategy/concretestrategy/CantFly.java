package org.gnuhpc.interview.designpattern.strategy.concretestrategy;

import org.gnuhpc.interview.designpattern.strategy.strategy.Flys;

//Class used if the Animal can't fly
public class CantFly implements Flys {
    public String fly() {
        return "I can't fly";
    }
}
