package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/4/5
 */
public class ReversePrint06 {
    private List<Integer> l = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        visit(head);
        int[] res = new int[l.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = l.get(i);
        }

        return res;
    }

    private void visit(ListNode head) {
        if (head == null) return;

        visit(head.next);
        l.add(head.val);
    }
}
