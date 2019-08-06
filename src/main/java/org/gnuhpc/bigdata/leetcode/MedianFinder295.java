package org.gnuhpc.bigdata.leetcode;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Copyright gnuhpc 19-8-5
 */
public class MedianFinder295 {
    TreeSet<Integer> treeSet;

    public MedianFinder295() {
        treeSet = new TreeSet<>();
    }

    public void addNum(int num) {
        treeSet.add(num);
    }

    public double findMedian() {
        return ((double)treeSet.last()+(double)treeSet.first())/2.0;
    }
}
