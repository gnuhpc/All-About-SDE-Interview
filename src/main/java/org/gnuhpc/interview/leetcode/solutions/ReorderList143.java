package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.asm.$ClassReader;
import com.google.inject.internal.cglib.reflect.$FastClass;
import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    Map<ListNode, ListNode> map = new HashMap<>();
    ListNode last = null;
    int count = 0;

    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        getParents(head, null);
        ListNode oldHead = head;
        ListNode p = dummy;
        boolean isHead = true;

        while (count > 0) {
            if (isHead) {
                p.next = head;
                p = p.next;
                if (count == 0) break;
                head = head.next;
            } else {
                p.next = last;
                p = p.next;
                if (count == 0) break;
                last = map.get(last);
            }

            count--;
            isHead = !isHead;

        }

        p.next = null;
        head = oldHead;
    }

    private void getParents(ListNode head, ListNode parent) {
        if (head == null) return;
        count++;
        if (head.next == null) last = head;
        map.put(head, parent);
        getParents(head.next, head);
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
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        reorderList2(head);
        Assert.assertArrayEquals(ListNode.toArray(head), new int[]{1, 5, 2, 4, 3});
    }
}
