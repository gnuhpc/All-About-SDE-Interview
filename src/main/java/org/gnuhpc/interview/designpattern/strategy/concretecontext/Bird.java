package org.gnuhpc.interview.designpattern.strategy.concretecontext;


import org.gnuhpc.interview.designpattern.strategy.concretestrategy.ItFlys;
import org.gnuhpc.interview.designpattern.strategy.context.Animal;

public class Bird extends Animal {

    // The constructor initializes all objects

    public Bird() {

        super();

        setSound("Tweet");

        // We set the Flys interface polymorphically
        // This sets the behavior as a non-flying Animal

        flyingType = new ItFlys();

    }

}