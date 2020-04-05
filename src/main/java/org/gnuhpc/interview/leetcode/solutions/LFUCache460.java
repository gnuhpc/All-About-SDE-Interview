package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/*
PriorityQueue方法，有几个问题，第一是时间复杂度为O(logn)，第二个是要维护globalAge自增，并发有问题。
 */
public class LFUCache460 {
    Queue<KVNode> queue;
    Map<Integer, KVNode> cache;
    int capacity;
    int globalAge = 0;

    public LFUCache460(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        queue = new PriorityQueue<>();
    }

    public int get(int key) {
        if (capacity <= 0) return -1;
        if (!cache.containsKey(key)) return -1;

        KVNode node = cache.get(key);
        globalAge++;
        node.age = globalAge;
        node.fre++;
        reHeapify(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;
        globalAge++;
        KVNode node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            node.age = ++globalAge;
            node.fre++;
            reHeapify(node);
        } else {
            if (queue.size() == capacity) {
                int oldKey = queue.poll().key;
                cache.remove(oldKey);
            }
            node = new KVNode(key, value, 1, globalAge);
            cache.put(key, node);
            queue.add(node);
        }
    }

    private void reHeapify(KVNode node) {
        queue.remove(node);
        queue.add(node);
    }

    private class KVNode implements Comparable<KVNode> {
        public int key;
        public int value;
        public int fre;
        public int age;

        public KVNode(int key, int value, int fre, int age) {
            this.key = key;
            this.value = value;
            this.fre = fre;
            this.age = age;
        }

        @Override
        public int compareTo(KVNode o) {
            if (this.fre == o.fre) {
                return (this.age - o.age);
            } else {
                return (this.fre - o.fre);
            }
        }
    }

    public static void main(String[] args) {
        LFUCache460 cache = new LFUCache460(2 /* 缓存容量 */);
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
