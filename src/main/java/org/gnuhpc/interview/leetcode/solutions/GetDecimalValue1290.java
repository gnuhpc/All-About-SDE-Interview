package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

/**
 * Copyright gnuhpc 2020/10/18
 */
public class GetDecimalValue1290 {
    int i = 0;
    int ans = 0;

    public int getDecimalValue(ListNode head) {
        if (head.next == null) return head.val;
        helper(head);
        return ans;
    }

    public void helper(ListNode head) {
        if (head == null) return;
        helper(head.next);
        ans += (int) Math.pow(2, i++) * head.val;
    }
}
