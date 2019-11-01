package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class RemoveNthFromEnd19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy, fast = dummy;

        for (int i = 0; i <n; i++) {
            fast = fast.next;
        }

        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    /*
    递归做法
     */
    int i;
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if(head == null) return null;
        head.next = removeNthFromEnd(head.next,n);
        i++;
        if(i==n) return head.next;
        return head;
    }

}
