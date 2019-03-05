package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class SwapPairs24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode n1 = head.next, n2 = head.next.next;
            // head->n1->n2->...
            // => head->n2->n1->...
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            // move to next pair
            head = n1;
        }

        return dummy.next;
    }

    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;
        else {
            // swap the two
            ListNode newHead = head.next;
            head.next = swapPairsRecursive(head.next.next);
            newHead.next = head;
            return newHead;
        }

    }

    // 4个指针的方案，更加清晰点
    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while(pre.next != null && pre.next.next != null){
            ListNode n1 = pre.next;
            ListNode n2 = n1.next;
            ListNode post = n2.next;

            n2.next = n1;
            n1.next = post;
            pre.next = n2;

            /**不要post节点的方案
             *  n1.next = n2.next;
             *  n2.next = n1;
             *  pre.next = n2;
             */

            pre = n1;
        }

        return dummy.next;

    }
}
