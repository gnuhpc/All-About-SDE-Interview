package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class RandomizedCollection381 {
    private List<Integer>               list; // val list for random access
    private Map<Integer, List<Integer>> posMap; //val -- position list
    private int                         tail;
    private Random                      random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection381() {
        list = new ArrayList<>();
        posMap = new HashMap<>();
        random = new Random();
        tail = 0;
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean isNotExist = true;
        list.add(tail, val);
        List<Integer> l = posMap.get(val);
        if (l == null) {
            l = new ArrayList<>();
            isNotExist = true;
        }
        else {
            isNotExist = false;
        }
        l.add(tail);
        posMap.put(val, l);

        tail++;
        return isNotExist;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        boolean isExist = true;
        if (posMap.containsKey(val)) {
            List<Integer> l = posMap.get(val);
            int pos = posMap.get(val).get(l.size() - 1);
            list.set(pos, null);
            l.remove(l.size() - 1);
            if (l.size() != 0) posMap.put(val, l);
            else posMap.remove(val);
            isExist = true;
        }
        else {
            isExist = false;
        }

        return isExist;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        int n = random.nextInt(tail + 1);

        Integer res = list.get(n);
        while (res == null) {
            res = getRandom();
        }

        return res;
    }

    @Test
    public void test() {
        RandomizedCollection381 collection = new RandomizedCollection381();

        System.out.println(collection.insert(100));
        System.out.println(collection.insert(100));
        System.out.println(collection.insert(200));
        System.out.println(collection.remove(100));
        System.out.println(collection.getRandom());
    }
}
