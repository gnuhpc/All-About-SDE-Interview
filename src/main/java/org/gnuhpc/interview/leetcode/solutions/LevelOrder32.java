package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder32 {
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        //队列
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        //根节点入队
        queue.add(root);
        while (!queue.isEmpty()) {
            //出队
            TreeNode node = queue.poll();
            //把结点值存放到list中
            list.add(node.val);
            //左右子节点如果不为空就加入到队列中
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        //把list转化为数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
