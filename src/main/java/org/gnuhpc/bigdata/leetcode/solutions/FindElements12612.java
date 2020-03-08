package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class FindElements12612 {
    /*
    Method2: DFS
     */

    HashSet<Integer> set;

    public FindElements12612(TreeNode root) {
        if (root == null) {
            return;
        }
        set = new HashSet<>();
        root.val = 0;
        set.add(root.val);
        robot(root.left, root, 1);
        robot(root.right, root, 2);
    }

    public void robot(TreeNode root, TreeNode pre, int delta) {
        if (root == null) {
            return;
        }
        root.val = 2 * pre.val + delta;
        set.add(root.val);
        robot(root.left, root, 1);
        robot(root.right, root, 2);
    }

    public boolean find(int target) {
        return set.contains(target);
    }

}
