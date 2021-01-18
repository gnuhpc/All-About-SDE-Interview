package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/1/16
 */
public class MyCircularQueue6221 {
    //指向第一个元素
    int head = 0;
    //指向最后一个元素的下一位，当tail = -1说明队列为空
    int tail = -1;
    int[] buffer;
    int capacity;

    public MyCircularQueue6221(int k) {
        /**
         * 浪费一个Int的空间，用于判断isFull
         */
        buffer = new int[k];
        capacity = k;
    }

    public boolean isEmpty() {
        //tail = -1说明队列为空
        return tail < 0;
    }

    public boolean isFull() {
        //当最后一个元素的下一位是队头，说明队列满了
        return head == tail;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        //如果队列为空，那么将队尾，队头重置
        if (isEmpty()) {
            tail = 0;
            head = 0;
        }
        buffer[tail++] = value;
        if (tail >= capacity) {
            tail = 0;
        }
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        //出队
        head++;
        if (head >= capacity) {
            head = 0;
        }
        if (isFull()) {
            tail = -1;
        }
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return buffer[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return buffer[(tail - 1 + capacity) % capacity];
    }
}
