package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class RandomizedSet380 {
    private HashMap<Integer, Integer> keyMap   = null;
    private HashMap<Integer, Integer> valueMap = null;
    int count;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet380() {
        keyMap = new HashMap<>();
        valueMap = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (keyMap.containsKey(val)) {
            return false;
        }
        else {
            keyMap.put(val, count);
            valueMap.put(count, val);
            count++;
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!keyMap.containsKey(val)) {
            return false;
        }
        else {
            int valueKey = keyMap.get(val);
            keyMap.remove(val);
            valueMap.remove(valueKey);
            if (valueKey == count) {
                count--;
            }
            //如果删除的不是最后一个,此处不能count--,否则会出现空洞,导致空指针
            return true;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random random = new Random();
        int n = random.nextInt(count);
        Integer res = valueMap.get(n);//在有空洞的情况下可能会有空指针,因此多试几次..
        while (res == null) {
            n = random.nextInt(count);
            res = valueMap.get(n);
        }
        return res;
    }


    @Test
    public void test() {
        RandomizedSet380 set = new RandomizedSet380();

        System.out.println(set.insert(100));
        System.out.println(set.insert(200));
        System.out.println(set.remove(100));
        for (int i = 0; i < 100000; i++) {
            System.out.println(set.getRandom());
        }
    }
}
