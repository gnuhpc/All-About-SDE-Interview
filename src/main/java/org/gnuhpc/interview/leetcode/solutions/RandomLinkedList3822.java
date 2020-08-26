package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */

/*
Reservoir Sampling for follow-up
 */
public class RandomLinkedList3822 {
    ListNode head;
    Random random;

    public RandomLinkedList3822(ListNode h) {
        head = h;
        random = new Random();
    }

    public int getRandom() {
        ListNode c = head;
        int r = c.val;
        for (int i = 1; c.next != null; i++) {
            c = c.next;
            int n = random.nextInt(i + 1);
            if (n < 1) r = c.val;
        }

        return r;
    }
}
