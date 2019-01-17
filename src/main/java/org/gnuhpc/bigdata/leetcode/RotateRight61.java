package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.Arrays;

public class RotateRight61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy, fast = dummy;

        int size = sizeOfList(head);
        if (size < k) {
            k %= sizeOfList(head);
        } else if (size == k){
            return head;
        }

        for (int i = 0; i < k; i++) {
            if (fast.next == null) fast = dummy;
            fast = fast.next;
        }

        if (fast.next == null){
            return dummy.next;
        }

        while (fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }

        fast.next = dummy.next;

        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }

    private int sizeOfList(ListNode head) {
        int size = 0;
        ListNode c = head;

        while (c!=null){
            c = c.next;
            size++;
        }
        return size;
    }

    @Test
    public void test(){
        ListNode head = ListNode.createList(new int[]{0,1,2});

        rotateRight(head,4);
    }
}
