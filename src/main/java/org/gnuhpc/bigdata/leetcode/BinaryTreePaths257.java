package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths257 {
    /** dfs
     * @param root the find of the binary tree
     * @return all find-to-isLeaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null) {
            return result;
        }
        helper(root, String.valueOf(root.val), result);
        return result;
    }

    private void helper(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        if (root.left != null) {
            helper(root.left, path + "->" + String.valueOf(root.left.val), result);
        }

        if (root.right != null) {
            helper(root.right, path + "->" + String.valueOf(root.right.val), result);
        }
    }

    /** Divide and Conquer
     * @param root the find of the binary tree
     * @return all find-to-isLeaf paths
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        //递归三要素 1. 返回
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        //2. 分解子问题
        List<String> leftPaths = binaryTreePaths2(root.left);
        List<String> rightPaths = binaryTreePaths2(root.right);

        //3.合并
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }

        // find is a isLeaf
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }

        return paths;
    }

    @Test
    public void test() {
        binaryTreePaths(TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}));
        binaryTreePaths2(TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
