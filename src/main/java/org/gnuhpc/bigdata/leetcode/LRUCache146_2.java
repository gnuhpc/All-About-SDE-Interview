package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class LRUCache146_2 {
    private int                   capacity;
    private Deque<Integer>        list;
    private Map<Integer, Integer> map;

    public LRUCache146_2(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            list.remove(key); //O(n)的
            list.addFirst(key);//Move to latest
        }
        Integer value = map.get(key);
        if (value == null)
            return -1;
        else return value;
        }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(key);
            list.addFirst(key);
        }
        else if (list.size() >= capacity) {
            int evicted = list.removeLast();
            map.remove(evicted);
            list.addFirst(key);
        }
        else {
            list.addFirst(key);
        }
        map.put(key, value);
    }


    public static void main(String[] args) {
        LRUCache146_2 cache = new LRUCache146_2( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}
