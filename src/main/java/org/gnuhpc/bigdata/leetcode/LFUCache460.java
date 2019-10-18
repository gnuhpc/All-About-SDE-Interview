package org.gnuhpc.bigdata.leetcode;

import java.util.*;

/*
PriorityQueue
 */
public class LFUCache460 {
    Queue<KVNode>        queue     = null;
    Map<Integer, KVNode> cache     = new HashMap<>();
    int                  cap       = 0;
    int                  globalAge = 0;

    public LFUCache460(int capacity) {
        cache = new HashMap<>();
        cap = capacity;
        if (cap != 0) queue = new PriorityQueue<>(capacity, (o1, o2) -> {
            if (o1.fre == o2.fre) {
                return (o1.age - o2.age);
            }
            else {
                return (o1.fre - o2.fre);
            }
        });
        else queue = null;
    }

    public int get(int key) {
        if (queue == null) return -1;
        if (!cache.containsKey(key)) return -1;
        KVNode node = cache.get(key);
        globalAge++;
        node.age = globalAge;
        node.fre++;
        reHeapify(node);
        return node.value;
    }


    public void put(int key, int value) {
        globalAge++;
        if (queue == null) return;
        KVNode node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            node.age = globalAge;
            node.fre++;
            reHeapify(node);
        }
        else {
            if (queue.size() == cap) {
                KVNode n = queue.poll();
                cache.remove(n.key);
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

    private class KVNode {
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
