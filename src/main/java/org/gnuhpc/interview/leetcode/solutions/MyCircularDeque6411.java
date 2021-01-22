package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/17
 */
public class MyCircularDeque6411 {
    class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    int capacity = 0;
    Node head, tail;
    int count = 0;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque6411(int k) {
        capacity = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (count == capacity) return false;
        Node newNode = new Node(value);
        if (count == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        count += 1;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (count == capacity) return false;
        Node newNode = new Node(value);
        if (count == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count += 1;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (count == 0) return false;
        if (count == 1) {
            head = tail = null;
            count -= 1;
            return true;
        }
        head = head.next;
        count -= 1;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (count == 0) return false;
        if (count == 1) {
            head = tail = null;
            count -= 1;
            return true;
        }
        Node tmp = head;
        // 因为是单链表，经过遍历，得到tail的上一个节点
        while (tmp.next != tail) {
            tmp = tmp.next;
        }
        tail = tmp;
        tmp.next = head;
        count -= 1;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (count == 0) return -1;
        return head.value;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (count == 0) return -1;
        return tail.value;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return count == capacity;
    }
}
