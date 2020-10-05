package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static org.gnuhpc.interview.datastructure.tree.basicimpl.TreeUtils.constructParentTreeNodeMap;

public class PruneTree814 {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        //碰上这种的直接返回，这一句是优化，可以删掉，但是会慢一点点
        if (root.left == null && root.right == null && root.val == 0) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        //左右叶子节点都处理后再次处理
        if (root.left == null && root.right == null && root.val == 0) return null;
        else return root;
    }

    Map<TreeNode, TreeNode> parentCache = new HashMap<>();
    boolean removeRoot = false;

    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) return null;

        constructParentTreeNodeMap(root, null, parentCache);

        dfs(root);
        if (removeRoot) return null;
        else
            return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        dfs(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            TreeNode pNode = parentCache.get(root);
            if (pNode == null) {
                if (root.val == 0) {
                    if (root.left == null && root.right == null) removeRoot = true;
                }
                return;
            }
            if (pNode.left == root) pNode.left = null;
            if (pNode.right == root) pNode.right = null;
        }
    }

}
