package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/10/12
 */
public class DeleteTreeNodes1273 {
    /**
     * @param nodes  节点的总数为 nodes 个
     * @param parent 第 i 个节点的值为 value[i]
     * @param value  第 i 个节点的父节点是 parent[i]
     * @return 删除节点值之和为 0 的每一棵子树。在完成所有删除之后，返回树中剩余节点的数目。
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        TreeNode root = null;
        TreeNode[] treeArr = new TreeNode[nodes];

        // 创建节点对象
        for (int i = 0; i < nodes; i++) {
            treeArr[i] = new TreeNode(value[i]);
            if (parent[i] == -1) root = treeArr[i];
        }
        // 给每个节点构造子结点
        for (int i = 0; i < nodes; i++) {
            if (parent[i] != -1) {
                // 第 i 个节点的父节点是 parent[i]
                treeArr[parent[i]].addTreeNode(treeArr[i]);
            }
        }
        // 计算每个子树的节点数量和总和值
        computeTree(root);

        if (root.sum == 0) return 0;
        // return nodes - removeZeroTreeNode(treeArr);
        return nodes - removeZeroTreeNode(root);
    }

    // 递归计算该及其孩子节点的“节点总数”和“值的加和”
    public void computeTree(TreeNode node) {
        node.sum = node.value;
        node.num = 1;
        for (TreeNode child : node.children) {
            computeTree(child);
            node.sum += child.sum;
            node.num += child.num;
        }
    }

    // 计算该节点及其孩子节点会被移除的节点总数
    public int removeZeroTreeNode(TreeNode node) {
        int removeNum = 0;  // 统计子树和为 0 的节点个数
        // 因为已经有了节点数组，所以不用 BFS，直接数即可
        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);
            if (child.sum == 0) {
                removeNum += child.num;
            } else {
                removeNum += removeZeroTreeNode(child);
            }
        }
        return removeNum;
    }

    /**
     * 多叉树的结点类
     */
    class TreeNode {
        int value;  // 节点权值
        int sum;    // 子树和
        int num;    // 子树的节点数量
        List<TreeNode> children;    // 子结点

        public TreeNode() {
            this.children = new ArrayList<TreeNode>();
        }

        public TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<TreeNode>();
        }

        /**
         * 添加子结点
         *
         * @param treeNode
         */
        public void addTreeNode(TreeNode treeNode) {
            this.children.add(treeNode);
        }
    }
}
