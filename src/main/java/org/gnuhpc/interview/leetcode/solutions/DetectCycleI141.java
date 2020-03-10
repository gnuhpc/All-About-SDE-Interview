package org.gnuhpc.interview.leetcode.solutions;


import org.gnuhpc.interview.leetcode.utils.ListNode;

public class DetectCycleI141 {

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        boolean isCycle = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                isCycle = true;
                break;
            }
        }

        return isCycle;

    }
}
