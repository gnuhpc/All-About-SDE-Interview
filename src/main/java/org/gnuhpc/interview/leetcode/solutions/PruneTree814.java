package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeUtils;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

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
}
