package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Flatten114 {
    /*
    Method1 : 递归,递归的时候求出左节点的最右孩子即可
     */

    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

    /*
    Method2: preorder非递归
     */

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            // connect
            node.left = null;
            if (stack.isEmpty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }

    /*
    Method3 : preorder 递归 前序遍历放在一个数组里，
     */

    public void flatten3(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorder(root,list);
        for(int i=0;i<list.size()-1;i++) {
            list.get(i).right=list.get(i+1);
            list.get(i).left = null;
        }

    }
    public void preorder(TreeNode root,List<TreeNode> list) {
        if(root!=null) {
            list.add(root);
            preorder(root.left,list);
            preorder(root.right,list);
        }
    }
}
