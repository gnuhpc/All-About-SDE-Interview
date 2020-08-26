package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

/**
 * Copyright gnuhpc 2020/4/10
 */
public class GetKthFromEnd22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head, slow = head;

        while (k > 0 && fast != null) {
            fast = fast.next;
            k--;
        }

        if (fast == null && k > 0) return null;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
