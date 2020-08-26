package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

/**
 * Copyright gnuhpc 2020/6/1
 */
public class KthToLast0202 {
    public int kthToLast(ListNode head, int k) {
        ListNode p = head;
        for (int i = 0; i < k; i++) {
            p = p.next;
        }

        ListNode q = head;
        while (p != null) {
            p = p.next;
            q = q.next;
        }

        return q.val;
    }
}
