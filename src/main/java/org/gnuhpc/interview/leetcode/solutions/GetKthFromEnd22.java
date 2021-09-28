package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

/**
 * Copyright gnuhpc 2020/4/10
 */
public class GetKthFromEnd22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head, slow = head;

        for(;k> 0 && fast!=null; k--) fast = fast.next;

        if (fast == null && k > 0) return null;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /*
    Golang Version:
func getKthFromEnd(head *ListNode, k int) *ListNode {
    fast, slow := head, head
	for i := 0; i < k; i++ {
		fast = fast.Next
	}
	for fast != nil {
		fast = fast.Next
		slow = slow.Next
	}
	return slow
}
     */
}
