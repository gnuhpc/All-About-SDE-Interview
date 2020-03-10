package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class RandomizedSet3802 {
    List<Integer> nums;
    Map<Integer, Integer> idx; //val -- idx ,建立索引为删除所用
    Random rand = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet3802() {
        nums = new ArrayList<>();
        idx = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (idx.containsKey(val)) return false;
        nums.add(val);
        idx.put(val, nums.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) return false;
        int loc = idx.get(val);
        if (loc < nums.size() - 1) { // not the last one then swap the last one with this val
            int lastone = nums.get(nums.size() - 1);
            nums.set(loc, lastone);
            idx.put(lastone, loc);
        }
        idx.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }


    @Test
    public void test() {
        RandomizedSet3802 set = new RandomizedSet3802();

        System.out.println(set.insert(1));
        System.out.println(set.remove(2));
        System.out.println(set.insert(2));
        System.out.println(set.getRandom());
        System.out.println(set.remove(1));
        System.out.println(set.insert(2));
    }
}
