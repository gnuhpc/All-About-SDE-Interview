package org.gnuhpc.bigdata.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2019/10/6
 */

/*
应对读多写少的场景
 */
public class TwoSum170 {
    Set<Integer> resultSet;
    Set<Integer> numberSet;

    /**
     * Initialize your data structure here.
     */
    public TwoSum170() {
        resultSet = new HashSet<>();
        numberSet = new HashSet<>();
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        Set<Integer> otherSet = new HashSet<>();

        for (int n : resultSet) {
            otherSet.add(n + number);
        }

        for (int n : numberSet) {
            otherSet.add(n + number);
        }

        numberSet.add(number);
        resultSet.addAll(otherSet);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        return resultSet.contains(value);
    }
}
