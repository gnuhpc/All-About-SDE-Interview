package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.cglib.proxy.$MethodProxy;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/8/17
 */
public class MyHashMap706 {
    private int[] array;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap706() {
        array = new int[16];
        Arrays.fill(array, -1);
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        if (key >= array.length) {
            int[] newArr = new int[key + 1];//或者也可以直接key*2
            Arrays.fill(newArr, -1);
            System.arraycopy(array, 0, newArr, 0, array.length);

            array = newArr;
        }

        array[key] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        if (key >= array.length) return -1;
        return array[key];
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        if (key < array.length && get(key) != -1) {
            array[key] = -1;
        }
    }

    public static void main(String[] args) {
        MyHashMap706 map = new MyHashMap706();

        map.put(200, 2);
        map.put(100, 3);

        System.out.println(map.get(200));
        System.out.println(map.get(102));

        map.remove(300);

        System.out.println(map.get(100));
    }
}
