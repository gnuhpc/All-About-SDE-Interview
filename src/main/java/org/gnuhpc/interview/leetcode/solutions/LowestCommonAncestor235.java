package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LowestCommonAncestor235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //分属在两边的，那么root一定是lca
        if ((p.val > root.val && q.val < root.val) ||
                (p.val < root.val && q.val > root.val))
            return root;

        //其中一个节点是root的，直接返回该节点
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;

        //都在一边的，递归查找
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);

        return null;
    }

    /*
    Method2 : 非递归写法
     */

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
    }


    /*
    Method3: preorder
     */

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new LinkedList<>();//存储探索过的节点

        TreeNode node = root;//指向root

        while (!stack.isEmpty() || node != null) {//先序遍历
            while (node != null) {
                //cout << node->val << endl;
                if ((node.val >= p.val && node.val <= q.val) ||
                        (node.val <= p.val && node.val >= q.val))
                    //根据搜索二叉树的特点，当且仅当节点值位于pq中间时返回
                    return node;

                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            node = node.right;
        }

        return node;
    }

}
