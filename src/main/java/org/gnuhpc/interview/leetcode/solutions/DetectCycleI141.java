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

    /*
    Method2 : 非常清奇的一种思路，就是修改节点，遍历过的都指向head，如有环则发现已经指向了head
     */
    public boolean hasCycle2(ListNode head) {
        // 空链环，或仅有一个节点且其后继为null
        if (head == null || head.next == null) {
            return false;
        }

        // 访问过的节点，都指向head
        // 只要遇到再次回到head，则表示有环
        ListNode current = head.next;
        while (current != null) {
            if (current == head) {
                return true;
            }
            ListNode prev = current;
            current = current.next;
            prev.next = head;
        }
        return false;
    }
}
