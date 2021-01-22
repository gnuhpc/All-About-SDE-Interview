package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DelNodes1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int x : to_delete) deleteSet.add(x);

        List<TreeNode> ans = new ArrayList<>();

        if (!deleteSet.contains(root.val)) ans.add(root);
        dfs(ans, null, root, deleteSet);

        return ans;
    }

    // 使用后续遍历方式删除节点，这样不会影响子树的删除
    private void dfs(List<TreeNode> ans, TreeNode parent, TreeNode root, Set<Integer> deleteSet) {
        if (root == null) return;

        dfs(ans, root, root.left, deleteSet);
        dfs(ans, root, root.right, deleteSet);

        //如果根节点要删除
        if (deleteSet.contains(root.val)) {
            if (root.left != null) ans.add(root.left);
            if (root.right != null) ans.add(root.right);
            // 切断父节点与本节点的连接
            if(parent!=null) {
                if (parent.left == root) parent.left = null;
                if (parent.right == root)  parent.right = null;
            }
        }
    }
}
