package org.gnuhpc.interview.datastructure.queue.basicimpl;

import java.util.NoSuchElementException;

/**
 * (front)Sample - B - C(rear)
 */
public class LinkedQueue<E> implements Queue<E> {

    Node<E> front;
    Node<E> rear;

    public LinkedQueue() {
        front = null;
        rear = null;
    }

    @Override
    public E add(E item) {
        if (front == null || rear == null) {
            front = rear = new Node<>(item, null);
        } else {
            rear.next = new Node<>(item, null);
            rear = rear.next;
        }
        return item;
    }

    @Override
    public E remove() {
        if (rear.next == front) {
            throw new NoSuchElementException();
        }
        E item = element();
        front = front.next;
        return item;
    }

    @Override
    public E element() {
        if (front == null) {
            throw new NoSuchElementException();
        }
        return front.item;
    }

    @Override
    public int size() {
        int count = 0;
        if (rear.next == front) return count;
        for (Node node = front; node != rear; node = node.next) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return rear.next == front;
    }

    @Override
    public void print() {
        Node<E> node;
        System.out.print("[");
        if (rear.next == front) {
            System.out.println("]");
            return;
        }
        for (node = front; node != rear; node = node.next) {
            System.out.print(node.item + ",");
        }
        System.out.println(node.item + "]");
    }

    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
