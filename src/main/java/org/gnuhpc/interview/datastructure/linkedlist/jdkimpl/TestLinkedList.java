package org.gnuhpc.interview.datastructure.linkedlist.jdkimpl;


import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class TestLinkedList {

    @Test
    public void shouldAddElementToList() {
        //given
        List<String> list = new LinkedList<>();

        //when
        list.add("a");

        //then
        assertEquals(list.size(), 1);

        //and when
        list.remove("a");
        assertEquals(list.size(), 0);

    }

    @Test
    public void shouldRetrieveHeadAndTailOfTheList() {
        //given
        LinkedList<String> list = new LinkedList<>();

        //when
        list.add("a");
        list.add("b");

        //a->b

        //then
        assertEquals(list.peekFirst(), "a");
        assertEquals(list.peekLast(), "b");
        assertEquals(list.pop(), "a"); //From Left

        list.push("c");
        // c->b, From left
        assertEquals(list.peekFirst(), "c");

    }

    @Test
    public void testVector() {
        //Thread-safe compared to the type Arraylist
        List<Integer> myVector = new Vector<>();

        myVector.add(1);
        myVector.add(2);

        myVector.forEach(System.out::println);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testModifiedList() {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        for (Integer i : l) {
            if (i > 4) {
                l.remove(i);
            }
        }
    }


}
