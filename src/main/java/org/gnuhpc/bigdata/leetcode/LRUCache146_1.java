package org.gnuhpc.bigdata.leetcode;

import java.util.*;

/*
PriorityQueue 是LRU、LFU的通解 ,但都不是最优解
 */
public class LRUCache146_1 {
    Queue<KVNode>        queue     = null;
    Map<Integer, KVNode> cache     = new HashMap<>();
    int                  cap       = 0;
    int                  globalAge = 0;

    public LRUCache146_1(int capacity) {
        cache = new HashMap<>();
        cap = capacity;
        queue = new PriorityQueue<>(capacity, Comparator.comparingInt(o -> o.age));
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        KVNode node = cache.get(key);
        globalAge++;
        node.age = globalAge;
        //PriorityQueue reheatify的方法
        reHeapify(node);

        return node.value;
    }


    public void put(int key, int value) {
        globalAge++;
        KVNode node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            node.age = globalAge;
            reHeapify(node);
        }
        else {
            if (queue.size() == cap) {
                KVNode n = queue.poll();
                cache.remove(n.key);
            }
            node = new KVNode(key, value, globalAge);
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
        public int age;

        public KVNode(int key, int value, int age) {
            this.key = key;
            this.value = value;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        LRUCache146_1 cacheLRU = new LRUCache146_1(2 /* 缓存容量 */);

        cacheLRU.put(2, 1);
        cacheLRU.put(1, 1);
        cacheLRU.put(2, 3);
        cacheLRU.put(4, 1);
        System.out.println(cacheLRU.get(1));       // 返回 -1 (未找到)
        System.out.println(cacheLRU.get(2));       // 返回  3
    }
}
