package org.gnuhpc.bigdata.designpattern.decorator.concretedecorator;

import org.gnuhpc.bigdata.designpattern.decorator.component.Pizza;
import org.gnuhpc.bigdata.designpattern.decorator.decorator.Decorator;

public class TomatoSauce extends Decorator {

    public TomatoSauce(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding Sauce");
    }

    // Returns the result of calling getDescription() for
    // PlainPizza, Mozzarella and then TomatoSauce

    public String getDescription() {

        return tempPizza.getDescription() + ", tomato sauce";

    }

    public double getCost() {

        System.out.println("Cost of Sauce: " + .35);

        return tempPizza.getCost() + .35;

    }

}