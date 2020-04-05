package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MergeTrees617 {
    /*
    Method 1: 递归版本
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            //两个均不为空的情况
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right, t2.right);
            return node;
        } else {
            //有为空的就返回另一个
            return t1 == null ? t2 : t1;
        }
    }

    /*
    Method 2: 非递归版本，层次遍历
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(t1);
        queue2.offer(t2);

        // 按层遍历t2，同步遍历t1，对比相同位置的节点差异。
        // t2有而t1没有的节点，则给t1创建一个。
        // t2有t1也有的节点，值相加更新t1的节点值。
        while (!queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            // t2与t1对应节点的值，累计到t1的节点
            node1.val += node2.val;

            if (node2.left != null) {
                // t1左孩子节点不存在，则t1左孩子直接指向t2对应的左孩子
                if (node1.left == null) {
                    node1.left = node2.left;
                } else {//如果都有，则不作处理，放入队列后边处理
                    //注意此处offer进去的都不会是null，因此取出相加的时候不用判断null
                    queue1.offer(node1.left);
                    queue2.offer(node2.left);
                }
            }

            if (node2.right != null) {
                // t1右孩子节点不存在，则t1右孩子直接指向t2对应的右孩子
                if (node1.right == null) {
                    node1.right = node2.right;
                } else {//如果都有，则不作处理，放入队列后边处理
                    //注意此处offer进去的都不会是null，因此取出相加的时候不用判断null
                    queue1.offer(node1.right);
                    queue2.offer(node2.right);
                }
            }
        }

        return t1;
    }

}
