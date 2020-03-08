package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class RandomizedSet380 {
    Map<Integer, Integer> valueMap;
    Map<Integer, Integer> indexMap;
    int                   size;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet380() {
        valueMap = new HashMap<>();
        indexMap = new HashMap<>();
        size = 0;

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) return false;
        size++;
        valueMap.put(size, val);
        indexMap.put(val, size);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) return false;
        //这里要考虑的是是否这个元素本来就是最后一个如果是最后一个不需要进行交换
        //获取值的位置
        int currentIndex = indexMap.get(val);
        if (currentIndex == size) {
            indexMap.remove(val);
            valueMap.remove(size--);
        }
        else {
            //记录地址的map删除
            indexMap.remove(val);
            //记录值的map删除
            valueMap.remove(currentIndex);
            //开始调换位置,将最后一个位置的数值放入空洞
            int lastValue = valueMap.get(size);
            valueMap.put(currentIndex, lastValue);
            //调换之后把重复值删除
            valueMap.remove(size--);

            //维护indexMap
            indexMap.put(lastValue, currentIndex);
        }
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int randIndex = new Random().nextInt(size) + 1;
        return valueMap.get(randIndex);
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
