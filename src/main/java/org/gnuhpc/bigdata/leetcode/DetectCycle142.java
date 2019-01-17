package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.ListNode;

public class DetectCycle142 {

    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        boolean isCycle = false;

        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow){
                isCycle = true;
                break;
            }
        }

        if (isCycle){
            slow = head;

            while(slow!=fast){
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        } else {
            return null;
        }

    }
}
