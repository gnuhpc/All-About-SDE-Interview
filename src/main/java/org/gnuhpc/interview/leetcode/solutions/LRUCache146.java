package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.systemdesign.practice.lru.LRUCache;

/*
投机取巧
 */
public class LRUCache146 {
    LRUCache<Integer, Integer> map;

    public LRUCache146(int capacity) {
        map = new LRUCache<>(capacity);
    }

    public int get(int key) {
        if (map == null || map.get(key) == null) return -1;
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (map == null) return;
        get(key);
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache146 cache = new LRUCache146(2 /* 缓存容量 */);

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
