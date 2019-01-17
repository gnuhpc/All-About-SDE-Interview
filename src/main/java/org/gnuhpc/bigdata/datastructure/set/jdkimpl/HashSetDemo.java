package org.gnuhpc.bigdata.datastructure.set.jdkimpl;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String args[]) {
        // create a hash set
        HashSet<String> hs = new HashSet<>();

        // add elements to the hash set
        hs.add("10");
        hs.add("21");
        hs.add("2");
        hs.add("2");
        hs.add("Sample");
        hs.add("Sample");
        hs.add("Sample");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");
        System.out.println(hs);
    }
}
