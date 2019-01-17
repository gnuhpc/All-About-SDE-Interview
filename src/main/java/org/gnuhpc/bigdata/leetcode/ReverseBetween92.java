package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class ReverseBetween92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cm = dummy , cn = dummy;
        for (int i = 0; i < m - 1 ; i++) {
            cm = cm.next;
        }

        for (int i = 0; i < n ; i++) {
            cn = cn.next;
        }
        ListNode prev = cm.next;
        ListNode cur = prev.next;

        while (prev != cn){
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
}
