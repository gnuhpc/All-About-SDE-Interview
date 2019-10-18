package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;

/*
Two HashMap + LinkedHashSet : bucket
 */
public class LFUCache4603 {
    /**
     Since we have get/put, we obviously need a hashmap. However, we need to evict least frequent used item so we need to keep the frequency information.
     Like LRU, we create a self-dine node structure that has : key, value, freq
     Also, we need to keep capacity and min_freq as global variable. So that we can use min_freq to evict entry.
     We can use another hashmap<Integer, LinkedHashSet<Node>> to store freq -> [list of Node that has the same freq].
     get(key): If key doesn't exist, return -1. Otherwise, get the node and call update(node). Return node.value
     put(key,value): If capacity <= 0, return. If key already existed, call update(node) and update its value.
     Otherwise, we create a new entry for the key and put the new node(freq=1) into the cacheMap.
     Now if cacheMap>capacity, we need to get the least frequent list of nodes from freqMap and delete the first node from list.
     Also delete it from cacheMap. In the end, reset minFreq = 1.
     update(node):First get the node.freq's list from freqMap. Delete this node from list. If list is empty and node.freq=minFreq. This means minFreq list is empty.
     Now we need to minFreq++. After this, We add 1 to node's freq, then put it back to freqMap to the new freq bucket.
     */
    class KVNode {
        int key;
        int value;
        int freq;
        public KVNode(int key, int value, int freq){
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
    private final int CAPACITY;
    private int minFreq;
    private HashMap<Integer, KVNode> cacheMap;
    private HashMap<Integer, LinkedHashSet<KVNode>> freqMap;
    public LFUCache4603(int capacity) {
        this.CAPACITY = capacity;
        this.minFreq = 1;
        this.cacheMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        if(!cacheMap.containsKey(key))
            return -1;
        KVNode node = cacheMap.get(key);
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(CAPACITY <= 0)
            return;
        if(cacheMap.containsKey(key)){
            KVNode node = cacheMap.get(key);
            update(node);
            node.value = value;
        }
        else{
            KVNode newNode = new KVNode(key, value, 1);
            cacheMap.put(key, newNode);
            if(!freqMap.containsKey(1))
                freqMap.put(1, new LinkedHashSet<>());
            freqMap.get(1).add(newNode);
            if(cacheMap.size() > CAPACITY){
                LinkedHashSet<KVNode> toBeEvictedList = freqMap.get(minFreq);
                KVNode toBeEvictedNode = toBeEvictedList.iterator().next();//LRU Eviction
                toBeEvictedList.remove(toBeEvictedNode);
                cacheMap.remove(toBeEvictedNode.key);
            }
            minFreq = 1;
        }
    }
    private void update(KVNode node){
        LinkedHashSet<KVNode> prevFreqNodeSet = freqMap.get(node.freq);
        prevFreqNodeSet.remove(node);
        if(prevFreqNodeSet.isEmpty() && node.freq == minFreq)
            minFreq++;
        node.freq++;
        if(!freqMap.containsKey(node.freq))
            freqMap.put(node.freq, new LinkedHashSet<>());
        freqMap.get(node.freq).add(node);
    }



    public static void main(String[] args) {
        LFUCache4603 cache = new LFUCache4603(2 /* 缓存容量 */);
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
