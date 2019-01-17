package org.gnuhpc.bigdata.designpattern.strategy.concretestrategy;

import org.gnuhpc.bigdata.designpattern.strategy.strategy.Flys;

//Class used if the Animal can't fly
public class CantFly implements Flys {
    public String fly() {
        return "I can't fly";
    }
}
