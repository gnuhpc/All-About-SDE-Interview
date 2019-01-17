package org.gnuhpc.bigdata.designpattern.iterator.iterator_builtin;

import org.gnuhpc.bigdata.designpattern.iterator.iterator_builtin.concreteaggregate.DinerMenu;
import org.gnuhpc.bigdata.designpattern.iterator.iterator_builtin.concreteaggregate.PancakeHouseMenu;

public class MainApp {

    public static void main(String args[]) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        System.out.println("\nMENU (with iterators)\n----\nBREAKFAST");
        printMenu(pancakeHouseMenu);
        System.out.println("\nLUNCH");
        printMenu(dinerMenu);
    }

    private static void printMenu(Iterable<String> menu) {

        for (String item : menu) {
            System.out.println(item);
        }
    }
}
