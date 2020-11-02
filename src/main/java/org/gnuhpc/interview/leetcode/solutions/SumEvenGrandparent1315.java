package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/10/30
 */
public class SumEvenGrandparent1315 {
    /*
    Method1 : GrandPa 递归  TODO
     */
    private int ans = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(null, null, root);
        return ans;
    }

    private void dfs(TreeNode grandparent, TreeNode parent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (grandparent != null && (grandparent.val & 1) == 0) {
            ans += node.val;
        }
        dfs(parent, node, node.left);
        dfs(parent, node, node.right);
    }

    /*
        Method 2: Parent Map
     */

    Map<TreeNode, TreeNode> map = new HashMap<>();

    public int sumEvenGrandparent2(TreeNode root) {
        dfs(root, null);
        int res = 0;
        for (TreeNode cur : map.keySet()) {
            TreeNode parent = map.get(cur);
            if (parent != null) {
                TreeNode grandNode = map.get(parent);
                if (grandNode != null && grandNode.val % 2 == 0) res += cur.val;
            }
        }
        return res;
    }

    private void dfs(TreeNode curr, TreeNode parent) {
        if (curr == null) return;
        map.put(curr, parent);
        dfs(curr.left, curr);
        dfs(curr.right, curr);
    }
}
