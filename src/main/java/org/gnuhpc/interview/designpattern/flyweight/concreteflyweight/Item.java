package org.gnuhpc.interview.designpattern.flyweight.concreteflyweight;

//Instances of Item will be the Flyweights
public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}