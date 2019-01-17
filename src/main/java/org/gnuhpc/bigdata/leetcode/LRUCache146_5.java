package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

//http://www.learn4master.com/data-structures/hashtable/leetcode-lru-cache-solution-in-java
/*
The key to for implementing an LRU is to put any recently used data at the head of the queue.

Before each insert, we check whether the queue is full. If the queue is full, we delete its last element, and insert the new node at the beginning of the queue.

If the queue is not full, we just add the data at the beginning of the queue.

When we want to delete a node or update a node, we need to quickly find the position of the node in the queue. So a HashTable or HashMap should be used to support the fast look up operation. In this case, the time complexity of the get operation is O(1).

Since we also need to efficiently remove a node in the middle of the queue, so a double linked list is needed.

There are two cases we need to remove a node in the middle of the queue:

The client specify that a node need to be removed.
A node is updated, it needs to be removed and insert at the head of the queue.
By using a double linked list, once we use the HashMap to located the position of the node to be removed, we can remove the node from the queue in O(1) time.

When we need update the cache for a key, we first use the HashMap to located the corresponding node, update the value, then we remove the node from the queue and put that node at the beginning of the Double Linked list.
 */
public class LRUCache146_5 {
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class Worker implements Callable<Integer> {
        private final LRUCache146_5 cache;

        public Worker(LRUCache146_5 lruCache) {
            this.cache = lruCache;
        }

        @Override
        public Integer call() {
            cache.put(1, 1);
            cache.put(2, 2);
            cache.put(3, 3);
            cache.put(4, 4);

            return cache.get(1);
        }
    }

    private static final ReentrantLock reLock = new ReentrantLock();

    private Map<Integer, Node> map = new HashMap<>();
    private int capacity;
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache146_5(int capacity) {
        this.capacity = capacity;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        reLock.lock();
        try {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node current = map.get(key);
            deleteNode(current);
            moveToLastestUsed(current);
            return current.val;
        } finally {
            reLock.unlock();
        }
    }

    public void put(int key, int value) {
        reLock.lock();
        //get will update the use frequency;
        try {
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
        } finally {
            reLock.unlock();
        }
    }

    private void deleteNode(Node current) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }

    private Node fetchLeastUsed() {
        return tail.prev;
    }

    private void moveToLastestUsed(Node current) {
        current.prev = head;
        current.next = head.next;

        current.prev.next = current;
        current.next.prev = current;
    }

    private void removeLeastUsed() {
        deleteNode(tail.prev);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LRUCache146_5 cache = new LRUCache146_5(10 /* 缓存容量 */);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<?>[] futureArray = new Future[10];

        for (int i = 0; i < 10; i++) {
            futureArray[i]=executorService.submit(new Worker(cache));
        }


        for (int i = 0; i < 10; i++) {
            System.out.println(futureArray[i].get());
        }

    }
}
