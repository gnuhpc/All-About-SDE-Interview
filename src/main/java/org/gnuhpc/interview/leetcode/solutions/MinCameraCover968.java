package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeUtils;
import org.junit.Test;

import java.util.*;

public class MinCameraCover968 {
    private int ans = 0;

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        if (dfs(root) == 2) ans++;
        return ans;
    }

    // 1：该节点安装了监视器 2：该节点可观，但没有安装监视器 3：该节点不可观
    private int dfs(TreeNode node) {
        if (node == null)
            return 1;
        int left = dfs(node.left), right = dfs(node.right);
        if (left == 2 || right == 2) {
            ans++;
            return 0;
        } else if (left == 0 || right == 0){
            return 1;
        } else
            return 2;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(
                new Integer[]{0,0,0,null,null,null,0}
        );

        System.out.println(minCameraCover(root));
    }
}
