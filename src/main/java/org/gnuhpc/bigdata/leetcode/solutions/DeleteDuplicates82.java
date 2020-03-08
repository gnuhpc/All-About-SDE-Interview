package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class DeleteDuplicates82 {
    public ListNode deleteDuplicates(ListNode head) {
        //设置起始节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;

        while (p.next != null && p.next.next != null) {
            if (p.next.val == p.next.next.val) {
                int val = p.next.val;
                while (p.next != null && p.next.val == val) {
                    p.next = p.next.next;
                }
            }
            else {
                p = p.next;
            }
        }

        return dummy.next;
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{1, 1});

        ListNode.print(deleteDuplicates(head));
    }
}
