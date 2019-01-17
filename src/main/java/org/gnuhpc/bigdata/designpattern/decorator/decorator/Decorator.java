package org.gnuhpc.bigdata.designpattern.decorator.decorator;

// Has a "Has a" relationship with Pizza. This is an
// Aggregation Relationship

import org.gnuhpc.bigdata.designpattern.decorator.component.Pizza;

public abstract class Decorator implements Pizza {

    //非常重要的一个！注意protected
    protected Pizza tempPizza;

    // Assigns the type instance to this attribute
    // of a Pizza

    // All decorators can dynamically customize the Pizza
    // instance PlainPizza because of this

    public Decorator(Pizza newPizza) {
        tempPizza = newPizza;
    }

    public String getDescription() {

        return tempPizza.getDescription();

    }

    public double getCost() {

        return tempPizza.getCost();

    }

}