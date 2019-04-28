package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
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
        ListNode mid = findLinkedListMid(head);
        ListNode midHead = mid.next;
        mid.next = null;//put an end for the first half
        ListNode head2 = reverseLinkedList(midHead);
        mergeLinkedList(head,head2);
    }

    private void mergeLinkedList(ListNode p, ListNode q) {
        while(q != null){
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

    private ListNode findLinkedListMid(ListNode head) {
        //前边已经处理过空，1,2三种情况了。
        ListNode fast = head, slow = head;

        while (fast != null && fast.next !=null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    @Test
    public void testMid(){
        ListNode head = ListNode.createList(new int[]{1,2,3,4});
        ListNode head2 = ListNode.createList(new int[]{1,2,3,4,5});
        Assert.assertEquals(findLinkedListMid(head).val,3);
        Assert.assertEquals(findLinkedListMid(head2).val,3);
    }

    @Test
    public void testReverse(){
        ListNode head = ListNode.createList(new int[]{1,2,3,4});
        head = reverseLinkedList(head);
        Assert.assertArrayEquals(ListNode.toArray(head),new int[]{4,3,2,1});
    }

    @Test
    public void test(){
        ListNode head = ListNode.createList(new int[]{1,2,3,4});
        reorderList(head);
        Assert.assertArrayEquals(ListNode.toArray(head), new int[]{1,4,2,3});
    }


    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null; //注意将这里赋空，变成2个列表,无需特殊处理

        merge(head, tail);
    }

    private void merge(ListNode p, ListNode q) {
        while(q != null){
            ListNode nextq = q.next;
            ListNode nextp = p.next;
            q.next = p.next;
            p.next = q;
            p = nextp;
            q = nextq;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
