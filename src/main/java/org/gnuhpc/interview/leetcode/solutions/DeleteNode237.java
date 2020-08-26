package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class DeleteNode237 {
    public void deleteNode(ListNode node) {
        if (node == null) return;
        ListNode next = node.next;
        while (next != null) {
            node.val = next.val;
            next = next.next;
            if (next == null) {
                node.next = null;
                break;
            } else {
                node = node.next;
            }
        }
    }
}
