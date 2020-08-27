package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/8/14
 */
public class FlipEquiv951 {
    /*
    Method1: BFS
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode curr1 = queue.poll();
            TreeNode curr2 = queue.poll();

            if (curr1 == null && curr2 == null) {
                continue;
            }
            if (!isEquals(curr1, curr2)) {
                return false;
            }
            if (isEquals(curr1.left, curr2.left) && isEquals(curr1.right, curr2.right)) {
                queue.offer(curr1.left);
                queue.offer(curr2.left);
                queue.offer(curr1.right);
                queue.offer(curr2.right);
            } else if (isEquals(curr1.left, curr2.right) && isEquals(curr1.right, curr2.left)) {
                queue.offer(curr1.left);
                queue.offer(curr2.right);
                queue.offer(curr1.right);
                queue.offer(curr2.left);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else return root1 != null && root2 != null && root1.val == root2.val;
    }
    /*
    Method2: Iteration
     */

    public boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null)
            return false;
        else if (root1 != null && root2 == null)
            return false;
        else if (root1 == null && root2 == null)
            return true;
        if (root1.val != root2.val)
            return false;
        boolean ll = flipEquiv(root1.left, root2.left);
        boolean lr = flipEquiv(root1.left, root2.right);
        boolean rl = flipEquiv(root1.right, root2.left);
        boolean rr = flipEquiv(root1.right, root2.right);
        return (ll && rr) || (lr && rl);
    }
}
