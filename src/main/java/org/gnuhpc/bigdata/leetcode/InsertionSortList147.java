package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class InsertionSortList147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        // 这个dummy的作用是，把head开头的链表一个个的插入到dummy开头的链表里
        // 所以这里不需要dummy.next = head;

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) { //定位要接上的部分
                node = node.next;
            }
            ListNode temp = head.next; //暂存
            head.next = node.next; //插入前边
            node.next = head;   //node 接上
            head = temp;
        }

        return dummy.next;
    }
}
