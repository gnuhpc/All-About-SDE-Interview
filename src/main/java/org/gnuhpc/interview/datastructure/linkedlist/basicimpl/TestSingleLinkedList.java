package org.gnuhpc.interview.datastructure.linkedlist.basicimpl;

import org.junit.Test;

public class TestSingleLinkedList {
    @Test
    public void testSingleLinkedList() {
        SingleLinkedList<Integer> list = new SingleLinkedList<Integer>(new int[]{1, 5, 6, 8, 3});
        list.printList();

        list.reverse();
        list.printList();

        list.reverseNonRecusive();
        list.printList();

        System.out.println(list.getNthFromEnd(2));
    }
}
