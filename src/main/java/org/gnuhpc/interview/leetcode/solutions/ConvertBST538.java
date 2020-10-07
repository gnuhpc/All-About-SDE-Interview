package org.gnuhpc.interview.leetcode.solutions;


import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Copyright gnuhpc 19-7-24
 */
//左中右是按从小到大的遍历，所以右中左遍历就是从大到小，因此右中左遍历，比例过程中不断的加上原来的数就行了。
public class ConvertBST538 {
    /*
    Method1: Inorder
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        return root;
    }

    //reverse inOrder to traverse root,
    //plus cache result to current node val and set current node val to cache result
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.right);
        root.val += sum;
        sum = root.val;
        inOrder(root.left);
    }

    /*
     method: non-recursion
     */

    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            node.val += sum;
            sum = node.val;
            node = node.left;
        }
        return root;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5, 2, 13});
        convertBST2(root);
    }

}

