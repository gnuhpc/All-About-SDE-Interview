package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;

public class GetIntersectionNode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode longList, shortList;
        int sizeA = getSize(headA);
        int sizeB = getSize(headB);

        int diff = Math.abs(sizeA - sizeB);

        if (sizeA > sizeB) {
            longList = headA;
            shortList = headB;
        } else {
            longList = headB;
            shortList = headA;
        }

        for (int i = 0; i < diff; i++) {
            longList = longList.next;
        }

        while (shortList != longList) {
            shortList = shortList.next;
            longList = longList.next;
        }

        return shortList;

    }

    private int getSize(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }
}
