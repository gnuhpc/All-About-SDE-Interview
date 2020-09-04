package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

/**
 * Copyright gnuhpc 2020/8/18
 */
public class DeleteNodes1474 {

    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pointer = dummy;
        while (pointer != null) {
            pointer = getPost(pointer, m);
            if (pointer == null) {
                break;
            }
            ListNode postNode = getPost(pointer, n + 1);
            pointer.next = postNode;
        }
        return head;
    }

    private ListNode getPost(ListNode node, int count) {
        for (int i = 0; i < count && node != null; i++) {
            node = node.next;
        }
        return node;
    }
}
