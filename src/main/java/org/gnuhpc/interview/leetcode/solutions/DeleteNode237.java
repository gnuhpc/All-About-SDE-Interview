package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class DeleteNode237 {
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        ListNode next = node.next;
        ListNode prev = null;

        while (next != null) {
            cur.val = next.val;
            prev = cur;
            cur = cur.next;
            next = next.next;
        }

        prev.next = null;
    }
}
