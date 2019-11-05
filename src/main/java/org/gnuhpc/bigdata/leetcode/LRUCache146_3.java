package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
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

//自己构造双向链表,速度最快
//head放most recent结果
public class LRUCache146_3 {
    class Node { //双向链表
        int  key;
        int  value;
        Node pre; //前指针
        Node next;//后指针

        public Node(int key, int value) {//构造函数
            this.key = key;
            this.value = value;
        }
    }

    static class Worker implements Callable<Integer> {
        private final LRUCache146_3 cache;

        public Worker(LRUCache146_3 lruCache) {
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

    HashMap<Integer, Node> map;
    int                    capacity; //LRU容量
    int                    count; //记录已用cache个数
    Node                   head, tail;

    public LRUCache146_3(int capacity) { //构造函数
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
        count = 0;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        deleteNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {//已经在cache中
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else {//新增到cache
            Node node = new Node(key, value);
            map.put(key, node); //HashMap中插入
            if (count < capacity) {//还有容量
                count++;
                addToHead(node);
            }
            else {//cache已满,删除链表尾的结点，插入当前结点
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }

    public void addToHead(Node entry) {//注意这个顺序的依赖
        //画个图，先对右边的进行，然后对左边的进行
        // begin is the actual first LRUCacheItem, which is after dummy head
        Node begin = this.head.next;
        if (entry == begin || entry == null) {
            return;
        }
        Node next = entry.next;
        Node previous = entry.pre;

        // remove entry between next and previous
        if (next != null) {
            next.pre = previous;
        }
        if (previous != null) {
            previous.next = next;
        }

        // Add entry between dummy head and begin
        begin.pre = entry;
        this.head.next = entry;

        // adjust entry
        entry.pre = this.head;
        entry.next = begin;
    }

    public void deleteNode(Node node) { //O(1) 这也是和方案2 的最大差别
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LRUCache146_3 cache = new LRUCache146_3(10 /* 缓存容量 */);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<?>[] futureArray = new Future[10];

        for (int i = 0; i < 10; i++) {
            futureArray[i] = executorService.submit(new Worker(cache));
        }


        for (int i = 0; i < 10; i++) {
            System.out.println(futureArray[i].get());
        }

    }
}
