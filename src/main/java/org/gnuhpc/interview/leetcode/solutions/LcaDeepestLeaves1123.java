package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LcaDeepestLeaves1123 {
    Map<TreeNode, Integer> depth;
    int max_depth;
    public TreeNode lcaDeepestLeaves(TreeNode root) {


        depth = new HashMap();
        depth.put(null, -1);
        getDepth(root, null);
        max_depth = -1;
        for (Integer d: depth.values())
            max_depth = Math.max(max_depth, d);

        return lca(root);
    }

    public void getDepth(TreeNode node, TreeNode parent) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            getDepth(node.left, node);
            getDepth(node.right, node);
        }
    }

    public TreeNode lca(TreeNode node) {
        if (node == null || depth.get(node) == max_depth)
            return node;
        TreeNode L = lca(node.left),
                R = lca(node.right);
        if (L != null && R != null) return node;
        if (L != null) return L;
        if (R != null) return R;
        return null;
    }
}
