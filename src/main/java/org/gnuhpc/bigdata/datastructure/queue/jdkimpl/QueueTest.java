package org.gnuhpc.bigdata.datastructure.queue.jdkimpl;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QueueTest {
    @Test
    public void testAddElements(){
        Queue<String> stringQueue = new LinkedList<>();
        stringQueue.offer("a"); //offer == add

        assertEquals(stringQueue.peek(),"a"); // peek == element
        assertFalse(stringQueue.isEmpty());
        assertEquals(stringQueue.poll(),"a"); //poll == remove
        assertTrue(stringQueue.isEmpty());

        stringQueue.add("a");
        stringQueue.add("b");
        stringQueue.remove();
        stringQueue.stream().forEach(System.out::println);
        System.out.println(stringQueue.element());
        stringQueue.offer("c");

        stringQueue.stream().forEach(System.out::println);

        //双端队列，如果希望当做队列则在一端添加，一端删除，希望当做堆栈则在同一端操作
        Deque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(1); //push actually is addFirst
        deque.addFirst(2);
        deque.addFirst(3);
        deque.stream().forEach(System.out::println); //[3,2,1]

    }

}
