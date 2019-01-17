package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class ReverseList206 {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next ==null)
            return head;

        ListNode cur = head;
        ListNode pre = null, next = null;

        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
