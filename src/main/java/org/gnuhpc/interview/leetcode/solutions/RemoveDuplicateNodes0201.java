package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/6/26
 */
public class RemoveDuplicateNodes0201 {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) return head;

        Set<Integer> nums = new HashSet<>();

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;

        while (p.next != null) {
            if (nums.contains(p.next.val)) {
                p.next = p.next.next;
            } else {
                nums.add(p.next.val);
                p = p.next;
            }
        }

        return dummy.next;

    }

    /*
    Method2: recursive
     */

    public ListNode removeDuplicateNodes2(ListNode head) {
        return removeDuplicateNodesHelper(head, new HashSet<>());
    }

    public ListNode removeDuplicateNodesHelper(ListNode head, Set<Integer> set) {
        if (head == null)
            return null;
        if (set.contains(head.val))
            return removeDuplicateNodesHelper(head.next, set);
        set.add(head.val);
        head.next = removeDuplicateNodesHelper(head.next, set);
        return head;
    }

}
