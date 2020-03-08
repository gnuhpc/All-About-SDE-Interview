package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

public class CountNodes222 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) { //说明左边是完整的，右边不完整, 一个完整的二叉树则是2^level
            return countNodes(root.right) + (int) Math.pow(2, left);
        }
        else {
            return countNodes(root.left) + (int) Math.pow(2, right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{
                1, 2, 3, 4, 5, 6, null
        });

        System.out.println(countNodes(root));


    }
}
