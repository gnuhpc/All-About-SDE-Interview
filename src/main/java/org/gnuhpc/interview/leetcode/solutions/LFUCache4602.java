package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/*
TreeMap + HashMap
 */
public class LFUCache4602 {

    HashMap<Integer, KVNode> cache;
    TreeMap<KVNode, Integer> freqMap;
    int stamp;
    int capacity;

    class KVNode implements Comparable<KVNode> {
        int frequency;
        int value;
        int stamp;

        public KVNode(int frequency, int value, int stamp) {
            this.frequency = frequency;
            this.value = value;
            this.stamp = stamp;
        }

        public int compareTo(KVNode that) {
            if (this.frequency == that.frequency) {
                return this.stamp - that.stamp;
            }
            return this.frequency - that.frequency;
        }
    }

    public LFUCache4602(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new TreeMap<>();
        this.stamp = 0;
    }

    public int get(int key) {
        if (capacity <= 0) {
            return -1;
        }
        if (!cache.containsKey(key)) {
            return -1;
        }
        KVNode node = cache.get(key);
        KVNode newNode = new KVNode(node.frequency + 1, node.value, ++stamp);
        freqMap.remove(node);
        cache.put(key, newNode);
        freqMap.put(newNode, key);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (cache.containsKey(key)) {
            KVNode tuple = cache.get(key);
            KVNode newTuple = new KVNode(tuple.frequency + 1, value, ++stamp);
            freqMap.remove(tuple);
            cache.put(key, newTuple);
            freqMap.put(newTuple, key);
        } else {
            if (freqMap.size() >= capacity) {
                int oldKey = freqMap.pollFirstEntry().getValue();
                cache.remove(oldKey);
            }
            KVNode newTuple = new KVNode(1, value, ++stamp);
            cache.put(key, newTuple);
            freqMap.put(newTuple, key);
        }
    }


    public static void main(String[] args) {
        LFUCache4602 cache = new LFUCache4602(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

    }
}
