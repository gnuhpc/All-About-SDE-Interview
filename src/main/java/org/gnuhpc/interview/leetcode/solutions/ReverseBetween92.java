package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class ReverseBetween92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cm = dummy, cn = dummy;
        for (int i = 0; i < m - 1; i++) {
            cm = cm.next;
        }

        for (int i = 0; i < n; i++) {
            cn = cn.next;
        }
        ListNode prev = cm.next;
        ListNode cur = prev.next;

        while (prev != cn) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        ListNode connNode = cm.next;
        cm.next = prev;
        connNode.next = cur;

        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m >= n || head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0); // 必须有个dummy，因为m=1时，head就不是第一个节点了。
        dummy.next = head;
        head = dummy;

        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }

        ListNode premNode = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode, postnNode = mNode.next;
        for (int i = m; i < n; i++) {
            if (postnNode == null) {
                return null;
            }
            ListNode temp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = temp;
        }
        mNode.next = postnNode;
        premNode.next = nNode;

        return dummy.next;
    }
}
