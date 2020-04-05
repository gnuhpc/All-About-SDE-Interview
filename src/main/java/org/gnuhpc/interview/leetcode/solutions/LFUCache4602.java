package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/*
TreeMap + HashMap
思路和时间复杂度一样，但是由于TreeMap是红黑树，remove 是O(logN)，因此eviction的时候速度更快
One of the differences is that remove(Object) and contains(Object) are linear O(N) in a normal heap based PriorityQueue (like Oracle's),
but O(log(N)) for a TreeSet/Map.
So if you have a large number of elements and do a lot of remove(Object) or contains(Object),
then a TreeSet/Map may be faster.
 */
public class LFUCache4602 {
    HashMap<Integer, KVNode> cache;
    TreeMap<KVNode, Integer> nkMap;
    int globalAge;
    int capacity;

    public LFUCache4602(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.nkMap = new TreeMap<>();
        this.globalAge = 0;
    }

    public int get(int key) {
        if (capacity <= 0) return -1;
        if (!cache.containsKey(key)) return -1;

        KVNode node = cache.get(key);
        reHeapify(key, node);
        return node.value;
    }

    public void put(int key, int value) {
        KVNode node;
        if (capacity <= 0) return;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            reHeapify(key, node);
        } else {
            if (nkMap.size() == capacity) { //eviction
                int oldKey = nkMap.pollFirstEntry().getValue();
                cache.remove(oldKey);
            }
            node = new KVNode(1, value, ++globalAge);
            nkMap.put(node, key);
            cache.put(key, node);
        }
    }

    private void reHeapify(int key, KVNode node) {
        nkMap.remove(node);
        node.freq++;
        node.age = ++globalAge;
        nkMap.put(node, key);
        cache.put(key, node);
    }

    class KVNode implements Comparable<KVNode> {
        int freq;
        int value;
        int age;

        public KVNode(int freq, int value, int age) {
            this.freq = freq;
            this.value = value;
            this.age = age;
        }

        public int compareTo(KVNode that) {
            if (this.freq == that.freq) {
                return this.age - that.age;
            }
            return this.freq - that.freq;
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
