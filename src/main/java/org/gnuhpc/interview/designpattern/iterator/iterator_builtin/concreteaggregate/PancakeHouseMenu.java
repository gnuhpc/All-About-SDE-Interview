package org.gnuhpc.interview.designpattern.iterator.iterator_builtin.concreteaggregate;

import org.gnuhpc.interview.designpattern.iterator.iterator_builtin.concreteiterator.PancakeHouseMenuIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class PancakeHouseMenu implements Iterable {
    ArrayList<String> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<String>();

        addItem("K&B's Pancake Breakfast");
        addItem("Regular Pancake Breakfast");
        addItem("Blueberry Pancakes");
        addItem("Waffles");
    }

    public void addItem(String name) {
        menuItems.add(name);
    }

    public ArrayList<String> getMenuItems() {
        return menuItems;
    }

    @Override
    public Iterator<String> iterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

    public String toString() {
        return "Pancake House Menu";
    }

    // other menu methods here
}
