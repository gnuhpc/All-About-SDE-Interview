package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;

public class SplitListToParts725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) {
            for (int i = 0; i < k; i++) {
                res[i] = null;
            }

            return res;
        }

        ListNode p = root;
        int len = 0;

        while (p != null) {
            len++;
            p = p.next;
        }

        int mod = len % k;
        int size = len / k;

        p = root;
        for (int i = 0; i < k && p != null; i++) {
            res[i] = p;
            int cursize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 0; j < cursize - 1; j++) {
                p = p.next;
            }
            ListNode next = p.next;
            p.next = null;
            p = next;
        }
        return res;
    }
}
