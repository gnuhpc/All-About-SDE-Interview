package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;
import org.junit.Test;

import java.util.List;

public class RemoveElements203 {
    public ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    // dummyNode，非递归，无需对头结点是否是val做特殊处理
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) {
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{1, 1});

        removeElements(head, 1);
    }
}
