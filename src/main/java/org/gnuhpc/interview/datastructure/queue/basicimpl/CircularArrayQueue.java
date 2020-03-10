package org.gnuhpc.interview.datastructure.queue.basicimpl;

public class CircularArrayQueue<E> implements Queue<E> {
    private static final int INITCAPACITY = 5;
    private int capacity; // capacity
    private E[] internalArray;

    private int front = 0;
    private int back = 0;

    public CircularArrayQueue() {
        this(INITCAPACITY);
    }


    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        internalArray = (E[]) new Object[capacity];
    }

    @Override
    public E add(E item) {
        if (size() == capacity - 1) {
            E[] newArray = (E[]) new Object[capacity * 2];
            System.arraycopy(internalArray, front, newArray, 0, capacity - front);
            System.arraycopy(internalArray, 0, newArray, capacity - front, back);

            internalArray = newArray;
            capacity = capacity * 2;
            front = 0;
            back = capacity / 2 - 1;
        }

        internalArray[back] = item;
        if (back < capacity - 1) {
            back++;
        } else {
            back = 0;
        }

        return item;
    }

    @Override
    public E remove() {
        E element = internalArray[front];
        front++;

        if (front == capacity) {
            front = 0;
        }

        if (size() == 0) {
            front = back = 0;
        }

        return element;
    }

    @Override
    public E element() {
        return internalArray[front];
    }

    public int size() {
        if (back > front)
            return back - front;

        return back - front + capacity;
    }

    public boolean isEmpty() {
        return (back == front);
    }

    @Override
    public void print() {
        if (isWrap()) {
            for (int i = front; i < capacity; i++) {
                System.out.println(internalArray[i]);
            }

            for (int i = 0; i < back; i++) {
                System.out.println(internalArray[i]);
            }
        } else {
            for (int i = front; i < back; i++) {
                System.out.println(internalArray[i]);
            }
        }
    }

    private boolean isWrap() {
        return !(front <= back);
    }

    public boolean isFull() {
        int diff = back - front;
        return diff == -1 || diff == (capacity - 1);
    }


}

