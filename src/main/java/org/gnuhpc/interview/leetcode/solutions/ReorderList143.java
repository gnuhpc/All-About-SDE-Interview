package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;
import org.junit.Assert;
import org.junit.Test;

/*
 * 1->2->3->4->null
 *
 * 1->2->null, 3->4->null
 * 1->2->null, 4->3->null
 * and then merge these two linked list using one element from l1 after another from l2
 *
 * */
public class ReorderList143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode mid = visitMiddlePreOneNode(head);
        ListNode midHead = mid.next;
        mid.next = null;//put an end for the first half
        ListNode head2 = reverseLinkedList(midHead);
        mergeLinkedList(head, head2);
    }

    private void mergeLinkedList(ListNode p, ListNode q) {
        while (q != null) {
            ListNode nextq = q.next;
            ListNode nextp = p.next;
            q.next = p.next;
            p.next = q;
            p = nextp;
            q = nextq;
        }
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode visitMiddlePreOneNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    @Test
    public void testMid() {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4});
        ListNode head2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        Assert.assertEquals(visitMiddlePreOneNode(head).val, 2);
        Assert.assertEquals(visitMiddlePreOneNode(head2).val, 3);
    }

    @Test
    public void testReverse() {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4});
        head = reverseLinkedList(head);
        Assert.assertArrayEquals(ListNode.toArray(head), new int[]{4, 3, 2, 1});
    }

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4});
        reorderList(head);
        Assert.assertArrayEquals(ListNode.toArray(head), new int[]{1, 4, 2, 3});
    }
}
