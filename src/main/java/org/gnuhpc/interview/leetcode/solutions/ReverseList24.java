package org.gnuhpc.interview.leetcode.solutions;


import org.gnuhpc.interview.leetcode.utils.ListNode;
import org.junit.Test;

public class ReverseList24 {
    //Method1: 非递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode cur = head;
        ListNode pre = null, next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    //Method 2: 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);

        ListNode reverseHead = reverseList2(head);
        System.out.println(reverseHead.val);
        System.out.println(reverseHead.next.val);
        System.out.println(reverseHead.next.next.val);

    }
}



