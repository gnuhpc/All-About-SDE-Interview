package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;

import java.util.LinkedList;
import java.util.List;

public class PartitionList86 {
    /*
   双链表
    */
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);

        ListNode p1 = dummy1, p2 = dummy2;

        ListNode p = head;

        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p;
            } else {
                p2.next = p;
                p2 = p;
            }

            p = p.next;
        }

        p1.next = dummy2.next;
        p2.next = null;

        return dummy1.next;
    }

    //干脆找个容器存起来，再重建，rebuild方法
    public ListNode partition2(ListNode head, int x) {
        List<ListNode> l1 = new LinkedList<>();
        List<ListNode> l2 = new LinkedList<>();

        ListNode p = head;

        while (p != null) {
            if (p.val < x) l1.add(p);
            else l2.add(p);
            p = p.next;
        }

        ListNode dummy = new ListNode(0);
        p = dummy;
        for (ListNode n : l1) {
            p.next = n;
            p = n;
        }

        for (ListNode n : l2) {
            p.next = n;
            p = n;
        }

        p.next = null;

        return dummy.next;
    }
}
