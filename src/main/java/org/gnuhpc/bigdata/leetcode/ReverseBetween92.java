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


    public ListNode reverseBetween2(ListNode head, int m, int n) {
        //保留此结点，避免m=1时，head结点要被反转，如果返回head将会出错
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode pre = dummy;
        for(int i = 1;i<m; i++){
            pre = pre.next;
        }
        ListNode mpre = pre;
        ListNode mNode = pre.next;
        ListNode cur = mNode.next;
        pre = mNode;
        for(int i = m;i<n;i++){
            ListNode post = cur.next;
            cur.next=pre;
            pre = cur;
            cur = post;
        }
        mpre.next = pre;
        mNode.next=cur;
        return dummy.next;
    }
}
