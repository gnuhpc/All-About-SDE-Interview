package org.gnuhpc.interview.leetcode.solutions;

import javafx.util.Pair;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/8/13
 */
public class SumRootToLeaf1022 {
    /*
    Method1: BFS. 利用pair记录每个节点和路径上的值，遍历到叶子节点是累加。
     */
    public int sumRootToLeaf(TreeNode root) {

        int res = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair(root, ""));
        while (!queue.isEmpty()) {
            Pair temp = queue.poll();
            TreeNode treeNode = (TreeNode) temp.getKey();
            String tmpNum = (String) temp.getValue();
            if (treeNode.left == null && treeNode.right == null)//叶子节点
            {
                res += Integer.parseInt(tmpNum + treeNode.val, 2);//转化字符串
                continue;
            }
            if (treeNode.left != null) queue.offer(new Pair(treeNode.left, tmpNum + treeNode.val));
            if (treeNode.right != null) queue.offer(new Pair(treeNode.right, tmpNum + treeNode.val));
            //将当前节点的左右节点和路径字符串入队
        }
        return res;
    }

    /*
    Method2: DFS
     */

    int total = 0;

    public int sumRootToLeaf2(TreeNode root) {
        dfs(root, 0);
        return total;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) return;
        sum = 2 * sum + root.val;
        if (root.left == null && root.right == null) {
            total += sum;
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
}
