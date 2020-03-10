package org.gnuhpc.interview.datastructure.hashtable.jdkexample;

import java.util.Map;
import java.util.TreeMap;

/*
Java TreeMap class is a red-black tree based implementation. It provides an efficient means of storing key-value pairs in sorted order.

The important points about Java TreeMap class are:

Java TreeMap contains values based on the key. It implements the NavigableMap interface and extends AbstractMap class.
Java TreeMap contains only unique elements.
Java TreeMap cannot have a null key but can have multiple null values.
Java TreeMap is non synchronized.
Java TreeMap maintains ascending order.
 */

/*

HashMap	                                TreeMap
1) HashMap can contain one null key.	TreeMap cannot contain any null key.
2) HashMap maintains no order.	        TreeMap maintains ascending order.

Time complexity: lowerKey(), higherKey(), put() and remove() are all O(logN)
 */
public class TestTreeMap {
    public static void main(String args[]) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(100, "Amit");
        map.put(102, "Ravi");
        map.put(101, "Vijay");
        map.put(103, "Rahul");

        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }

        //Maintains descending order
        System.out.println("descendingMap: " + map.descendingMap());
        //Returns key-value pairs whose keys are less than or equal to the specified key.
        System.out.println("headMap: " + map.headMap(102, true));
        //Returns key-value pairs whose keys are greater than or equal to the specified key.
        System.out.println("tailMap: " + map.tailMap(102, true));
        //Returns key-value pairs exists in between the specified key.
        System.out.println("subMap: " + map.subMap(100, false, 102, true));

        //Returns key-value pairs, Cell: null
        System.out.println("Cell: " + map.ceilingEntry(104));

        //Cell: 100=Amit
        System.out.println("Cell: " + map.ceilingEntry(91));

        System.out.println("Lower Key: " + map.lowerKey(101)); //<    100
        System.out.println("Floor Key: " + map.floorKey(101)); //<=   101
        System.out.println("Celling Key: " + map.ceilingKey(101)); //>=  101

    }
}
