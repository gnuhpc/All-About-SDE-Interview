package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;
import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

public class ReverseKGroup25 {
    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = null;

        Utils.printLinkedList(reverseKGroup(n1, 2));
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode pre = fake;
        int i = 0;

        ListNode p = head;
        while (p != null) {
            i++;
            if (i % k == 0) {
                pre = reverse(pre, p.next);
                p = pre.next;
            } else {
                p = p.next;
            }
        }

        return fake.next;
    }

    /*
     * 0->1->2->3->4->5->6
     * |           |
     * pre        next
     *
     * after calling pre = reverse(pre, next)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     */
    public ListNode reverse(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode curr = last.next;

        while (curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }

        return last;
    }

}
