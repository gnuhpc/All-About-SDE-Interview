package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.linkedlist.basicimpl.ListNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2020/5/7
 */
public class IsSubPath1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;

        else {
            return isPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
        }
    }

    private boolean isPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (head.val != root.val) return false;
        else {
            return isPath(head.next, root.left) || isPath(head.next, root.right);
        }
    }
}
