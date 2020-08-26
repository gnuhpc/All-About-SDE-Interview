package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
import org.junit.Test;

public class OddEvenList328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);

        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        ListNode cur = dummy;

        boolean isOdd = true;
        while (cur != null) {
            if (isOdd) {
                cur1.next = cur.next;
                if (cur1.next != null) {
                    cur1 = cur1.next;
                }
            } else {
                cur2.next = cur.next;
                if (cur2.next != null) {
                    cur2 = cur2.next;
                }
            }

            cur = cur.next;
            isOdd = !isOdd;
        }

        cur1.next = dummy2.next;
        cur2.next = null;

        return dummy1.next;
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{});
        ListNode.print(oddEvenList(head));
    }
}
