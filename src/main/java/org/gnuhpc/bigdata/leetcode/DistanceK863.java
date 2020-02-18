package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.*;

public class DistanceK863 {
    private List<Integer> list = new ArrayList<>();
    private int k;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null) {
            return Collections.emptyList();
        }
        this.k = k;
        dfs(root, target);
        return list;
    }

    /**
     * @param root 起始点
     * @param target 终止点
     * @return 返回两点之间的距离（边的个数）
     */
    private int dfs(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            addNodes(root, k);
            return 1;
        }
        int l = dfs(root.left, target);
        int r = dfs(root.right, target);
        if (l > 0 && l < k) {
            //在左分支找到了，但是从root.left到target不足k，那么就是在右边找
            addNodes(root.right, k - l - 1);
            return l + 1;
        }
        if (r > 0 && r < k) {
            //在右分支找到了，但是从root.right到target不足k，那么就是在左边找
            addNodes(root.left, k - r - 1);
            return r + 1;
        }
        if (l == k || r == k) {
            addNodes(root, 0);
            return k + 1;
        }
        return -1;
    }

    /**
     * 将与根节点距离为distance的子节点添加到结果集中
     * @param root 根节点
     * @param distance 与根节点的距离
     */
    private void addNodes(TreeNode root, int distance) {
        if (root == null) {
            return;
        }
        if (distance == 0) {
            list.add(root.val);
            return;
        }
        addNodes(root.left, distance - 1);
        addNodes(root.right, distance - 1);
    }

}
