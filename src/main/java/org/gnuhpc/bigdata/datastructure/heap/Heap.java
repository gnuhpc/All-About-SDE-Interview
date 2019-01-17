package org.gnuhpc.bigdata.datastructure.heap;

import java.util.Arrays;
import java.util.Vector;

/*
* 最大堆和最小堆在这些操作上都是相同的，注意在头部采用向下整理堆，尾部采用向上整理堆
* 堆是完全二叉树(最后一层从左往右依次补齐)，但不一定是满二叉树
* */
public abstract class Heap {
    protected Vector<Integer> storage;
    protected abstract void heapify_down(int i);  //poll的时候用，build也用，O(logn) 因为堆是一个完全二叉树，最坏的情况下需要从根节点出发达到叶子结点，等于完全二叉树的高度
    protected abstract void heapify_up(int i); //add时候用
        // insert specified key into the heap
        // vector to store heap elements

    // constructor: use default initial capacity of vector
    public Heap() {
        storage = new Vector<>();
    }

    // constructor: set custom initial capacity for vector
    public Heap(int capacity) {
        storage = new Vector<>(capacity);
    }

    public Heap(int[] sources) {
        storage = new Vector<>();
        build(sources);
    }


    public void add(Integer key)
    {
        // insert the new element to the end of the vector
        storage.addElement(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapify_up(index);
    }

      // return size of the heap
    public int size()
    {
        return storage.size();
    }

    // check if heap is empty or not
    public Boolean isEmpty()
    {
        return storage.isEmpty();
    }



    // function to remove and return element with highest priority
    // (present at root). It returns null if queue is empty
    //O(logn)
    public Integer poll()
    {
        try {
            // if heap is empty, throw an exception
            if (size() == 0)
                throw new Exception("Index is out of range (Heap underflow)");

            // element with highest priority
            int root = storage.firstElement();	// or storage.get(0);

            // replace the root of the heap with the last element of the vector
            storage.setElementAt(storage.lastElement(), 0);
            storage.remove(size()-1);

            // call heapify-down on root node
            heapify_down(0); //时间复杂度的关键所在

            // return root element
            return root;
        }
        // catch and print the exception
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    // function to return, but does not remove, element with highest priority
    // (present at root). It returns null if queue is empty
    public Integer peek()
    {
        try {
            // if heap has no elements, throw an exception
            if (size() == 0)
                throw new Exception("Index out of range (Heap underflow)");

            // else return the top (first) element
            return storage.firstElement();	// or storage.get(0);
        }
        // catch the exception and print it, and return null
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    // function to remove all elements from priority queue
    public void clear()
    {
        System.out.print("Emptying queue: ");
        while (!storage.isEmpty()) {
            System.out.print(poll() + " ");
        }
        System.out.println();
    }

    // returns true if queue contains the specified element
    public Boolean contains(Integer i)
    {
        return storage.contains(i);
    }

    // returns an array containing all elements in the queue
    public Integer[] toArray()
    {
        return storage.toArray(new Integer[size()]);
    }

    //O(nlogn)
    public void build(int[] source){
        assert(source!=null);
        storage = new Vector<>();
        Arrays.stream(source).forEach(i-> storage.add(i));
        for (int i = 0; i<= storage.size() / 2 - 1; i++) {
            heapify_down(i); //处理非叶子节点
        }
    }

    public void print() {
        if (storage == null)
            System.out.print("null");
        int iMax = size() - 1, i;
        if (iMax == -1)
            System.out.print("[]");

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (i = 0; i < iMax; i++) {
            b.append(storage.get(i));
            b.append(", ");
        }
        System.out.println(b.append(storage.get(i)).append(']').toString());
    }    // return PARENT of storage.get(i)

    protected int PARENT(int i)
    {
        // if i is already a root node
        if (i == 0)
            return 0;

        return (i - 1) / 2;
    }

    // return left child of storage.get(i)
    protected int LEFT(int i)
    {
        return (2 * i + 1);
    }

    // return right child of storage.get(i)
    protected int RIGHT(int i)
    {
        return (2 * i + 2);
    }

    // swap profits at two indexes
    protected void swap(int x, int y)
    {
        // swap with child having greater value
        Integer temp = storage.get(x);
        storage.setElementAt(storage.get(y), x);
        storage.setElementAt(temp, y);
    }
}
