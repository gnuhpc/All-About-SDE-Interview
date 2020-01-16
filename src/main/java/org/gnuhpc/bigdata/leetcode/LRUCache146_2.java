package org.gnuhpc.bigdata.leetcode;

import java.util.*;

//面试做到这一步是第二步
public class LRUCache146_2 {
    private int                   capacity;
    //Head -> most recent
    private Deque<Integer>        list;
    private Map<Integer, Integer> map;

    public LRUCache146_2(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        list.remove(key); //O(n)的 ,这里面比自己维护要慢得多
        list.addFirst(key);//Move to latest
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(key);//update LRU deque
        } else{
            if (list.size() >= capacity) { //evicted
                int evicted = list.removeLast();
                map.remove(evicted);
            }
        }

        map.put(key,value);//update cache
        list.addFirst(key);
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
