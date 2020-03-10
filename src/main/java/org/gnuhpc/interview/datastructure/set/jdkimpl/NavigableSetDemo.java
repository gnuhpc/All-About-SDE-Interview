package org.gnuhpc.interview.datastructure.set.jdkimpl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetDemo {
    public static void main(String[] args) {

        NavigableSet<String> navigableSet = new TreeSet<>(Arrays.asList(
                "X", "B", "Sample", "Z", "T"));

        Iterator<String> iterator = navigableSet.descendingIterator();

        System.out.println("Original Set :");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = navigableSet.iterator();

        System.out.println("Sorted Navigable Set :");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.printf("Head Set : %s.%n", navigableSet.headSet("X"));

        System.out.printf("Tail Set : %s.%n", navigableSet.tailSet("T", false));

        System.out.printf("Sub Set : %s.%n",
                navigableSet.subSet("B", true, "X", true));

        System.out.printf("Last Element : %s%n", navigableSet.last());

        System.out.printf("First Element : %s%n", navigableSet.first());

        System.out.printf("Reverse Set : %s%n", navigableSet.descendingSet());

        System.out.printf("Original Set : %s%n", navigableSet);

    }
}
