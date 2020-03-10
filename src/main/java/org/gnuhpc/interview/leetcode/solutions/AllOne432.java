package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/*
If we plan to achieve O(1), some options are out: Tree, Heap, which require O(log N).

One of the feasible options left is a sorted List (max at the head, min at tail) + Maps for lookups.

Deque: keep a sorted (descending) list based on frequency.
Set in each Deque Node: keep keys that share the same frequency.
2 Maps: O(1) Node lookup given a key or a frequency.
 */
public class AllOne432 {
    class Node {
        int val;
        Set<String> keys;
        Node pre;
        Node next;

        public Node(int a) {
            val = a;
            keys = new HashSet<>();
        }
    }

    // head: used as handle
    Node head;

    // tail: used as handle
    Node tail;

    // key -> Node
    Map<String, Node> keyMap;

    // count -> Node
    Map<Integer, Node> indexMap;

    /**
     * Initialize your data structure here.
     */
    public AllOne432() {
        keyMap = new HashMap<>();
        indexMap = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        int newCount = !keyMap.containsKey(key) ? 1 : keyMap.get(key).val + 1;
        Node oldNode = !keyMap.containsKey(key) ? tail : keyMap.get(key);

        if (!indexMap.containsKey(newCount)) {
            Node newNode = new Node(newCount);
            indexMap.put(newCount, newNode);
            insertBefore(newNode, oldNode);
        }

        indexMap.get(newCount).keys.add(key);
        keyMap.put(key, indexMap.get(newCount));
        oldNode.keys.remove(key);
        refreshNode(oldNode);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!keyMap.containsKey(key)) return;

        Node oldNode = keyMap.get(key);
        if (oldNode.val == 1) {
            keyMap.remove(key);
        } else {
            int newCount = oldNode.val - 1;
            if (!indexMap.containsKey(newCount)) {
                Node newNode = new Node(newCount);
                indexMap.put(newCount, newNode);
                insertAfter(newNode, oldNode);
            }
            indexMap.get(newCount).keys.add(key);
            keyMap.put(key, indexMap.get(newCount));
        }
        oldNode.keys.remove(key);
        refreshNode(oldNode);
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return tail.pre == head ? "" : tail.pre.keys.iterator().next();
    }

    // if node.keys is empty, remove this node
    void refreshNode(Node node) {
        if (node == head || node == tail)
            return;
        if (node.keys.size() == 0) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            indexMap.remove(node.val);
        }
    }

    void insertBefore(Node newNode, Node ref) {
        newNode.pre = ref.pre;
        newNode.next = ref;
        newNode.pre.next = newNode;
        newNode.next.pre = newNode;
    }

    void insertAfter(Node newNode, Node ref) {
        newNode.next = ref.next;
        newNode.pre = ref;
        newNode.pre.next = newNode;
        newNode.next.pre = newNode;
    }


    @Test
    public void test() {
        AllOne432 allOne = new AllOne432();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println(allOne.getMinKey());
        allOne.dec("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
}
