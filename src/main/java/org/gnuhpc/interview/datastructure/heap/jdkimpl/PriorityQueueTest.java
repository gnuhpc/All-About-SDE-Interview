package org.gnuhpc.interview.datastructure.heap.jdkimpl;

import org.junit.Test;

import java.util.*;

//PriorityQueue本质上不是Queue，而是Heap，它只是具备了Queue的接口
//Minheap by default
public class PriorityQueueTest {
    @Test
    public void test() {

        Queue<Integer> minheap = new PriorityQueue<>();
        //add elements
        minheap.addAll(Arrays.asList(8, 9, 1, 2, 3, 4, 5));
        System.out.println("Minheap---------------------");
        System.out.println(Arrays.toString(minheap.toArray()));
        System.out.println("Peek one: " + minheap.peek());
        while (!minheap.isEmpty()) {
            System.out.println("Min and Removing element is : " + minheap.element());
            minheap.remove();
        }

        //create a new object and add a custom comparator that provides the "MaxHeap" behaviour.
        Queue<Integer> maxheap = new PriorityQueue<>(1,
                (o1, o2) -> o2 - o1);
        System.out.println("Maxheap---------------------");
        //add elements
        maxheap.addAll(Arrays.asList(8, 9, 1, 2, 3, 4, 5));
        System.out.println(Arrays.toString(maxheap.toArray()));
        while (!maxheap.isEmpty()) {
            System.out.println("Max and Removing element is : " + maxheap.element());
            maxheap.remove();
        }

    }
}
