package org.gnuhpc.bigdata.datastructure.set.jdkimpl;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        // Creating a TreeSet with a custom Comparator (Descending  Order)
        SortedSet<String> fruits = new TreeSet<>(Comparator.reverseOrder());

        /*
            The above TreeSet with the custom Comparator is the concise form of the following:
            SortedSet<String> fruits = new TreeSet<>(new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s2.compareTo(s1);
                }
            });
        */

        // Adding new elements to a TreeSet
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Pineapple");
        fruits.add("Orange");

        System.out.println("Fruits Set : " + fruits);

        System.out.println(fruits.tailSet("Banana"));
        System.out.println(fruits.subSet("Orange","Banana"));
        // creating a TreeSet
        TreeSet <Integer>treeadd = new TreeSet<Integer>();

        // adding in the tree set
        treeadd.add(12);
        treeadd.add(11);
        treeadd.add(16);
        treeadd.add(15);

        // getting ceiling value for 13
        System.out.println("Ceiling value for 13: "+treeadd.ceiling(13));//15
    }
}
