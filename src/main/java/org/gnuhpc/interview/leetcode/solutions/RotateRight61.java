package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.Arrays;

public class RotateRight61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode p = head;
        int len = 1;
        while (p.next != null) {
            p = p.next;
            len++;
        }
        p.next = head;
        k %= len;
        for (int i = 0; i < len - k; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{0, 1, 2});

        rotateRight(head, 4);
    }
}
