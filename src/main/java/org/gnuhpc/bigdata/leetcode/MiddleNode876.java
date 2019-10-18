package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;
import org.junit.Test;

public class MiddleNode876 {
    //TODO 模板
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode fast = head , slow = head;

        while(fast!=null){
            fast = fast.next;
            if(fast == null) break;
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    @Test
    public void test(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        middleNode(n1);
    }
}
