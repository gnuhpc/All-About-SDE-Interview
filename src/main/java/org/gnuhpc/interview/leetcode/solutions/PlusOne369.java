package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class PlusOne369 {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }
        int carry = helper(head);
        if (carry == 1) {
            ListNode res = new ListNode(1);
            res.next = head;
            return res;
        }
        return head;
    }

    int helper(ListNode node) {
        if (node == null) {
            return 1;
        }
        int carry = helper(node.next);
        int sum = node.val + carry;
        node.val = sum % 10;
        return sum / 10;
    }

    /*

     */
    public ListNode plusOne2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            j.val++;
        } else {
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }

        if (dummy.val == 0) {
            return dummy.next;
        }

        return dummy;
    }

}
