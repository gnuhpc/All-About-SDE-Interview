package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */
public class RandomLinkedList382 {
    /**
     * @param head The linked list's head.
     * Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    private Random random;
    private List<ListNode> list;

    public RandomLinkedList382(ListNode head) {
        random = new Random();
        list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        int n = random.nextInt(list.size());
        return list.get(n).val;
    }
}
