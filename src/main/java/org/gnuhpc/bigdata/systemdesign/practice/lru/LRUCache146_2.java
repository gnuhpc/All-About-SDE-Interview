package org.gnuhpc.bigdata.systemdesign.practice.lru;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache146_2 {
    class Node {
        public int key;
        public int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    private Map<Integer, Node> map = new HashMap<>();
    private int                capacity;
    private Deque<Node>        l   = new LinkedList<>();

    public LRUCache146_2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node current = map.get(key);
        deleteNode(current);
        moveToLastestUsed(current);
        return current.val;
    }

    public void put(int key, int value) {
        //get will update the use frequency;
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        if (map.size() == capacity) {
            Node n = fetchLeastUsed();
            removeLeastUsed();
            map.remove(n.key);
        }
        Node insert = new Node(key, value);
        map.put(key, insert);
        moveToLastestUsed(insert);
    }

    private void deleteNode(Node current) {
        l.remove(current);
    }

    private Node fetchLeastUsed() {
        return l.getLast();
    }

    private void moveToLastestUsed(Node n) {
        l.addFirst(n);
    }

    private void removeLeastUsed() {
        l.removeLast();
    }

    public static void main(String[] args) {
        LRUCache146_2 cache = new LRUCache146_2(2 /* 缓存容量 */);

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
