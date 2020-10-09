package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.Set;
import java.util.TreeSet;

public class FindSecondMinimumValue671 {
    /*
    Method1: 这是个特殊的树，如果有子节点，那么树的root val等于其中小的，那么其中大的就是
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }

        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        if (left != -1) {
            return left;
        }

        if (right != -1)
            return right;


        return -1;

    }

    /*
    Method2: 这是个通解
     */
    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        dfs(root, set);

        Integer res = set.pollFirst();
        if (res == null) return -1;

        res = set.pollFirst();
        if (res != null) return res;
        else return -1;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }
}
