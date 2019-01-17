package org.gnuhpc.bigdata.designpattern.prototype;

public class Sheep implements Animal {

    public Sheep() {

        System.out.println("Sheep is Made");

    }

    public Animal makeCopy() throws CloneNotSupportedException {

        System.out.println("Sheep is Being Made");

        return (Sheep) super.clone();
    }

    public String toString() {

        return "Dolly is my Hero, Baaaaa";

    }

}