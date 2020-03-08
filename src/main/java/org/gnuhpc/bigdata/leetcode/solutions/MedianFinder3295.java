package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.Collections;

// add by tina,借助数组暴力解
public class MedianFinder3295 {
    ArrayList<Integer> arr;

    /**
     * initialize your data structure here.
     */
    public MedianFinder3295() {
        arr = new ArrayList<>();
    }

    public void addNum(int num) {
        arr.add(num);
        Collections.sort(arr);
    }

    public double findMedian() {
        int len = arr.size();
        if (len % 2 == 0) return (double) (arr.get(len / 2 - 1) + arr.get(len / 2)) / 2.0;
        else return arr.get(len / 2);
    }
}
