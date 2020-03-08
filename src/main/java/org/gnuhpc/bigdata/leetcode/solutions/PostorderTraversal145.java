package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeTraversal;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        return TreeTraversal.postorder(root);
    }


    /*
    Method2: Stack 和前序遍历差不多
     */
    public List<Integer> postorderTraversalNonRecursive(TreeNode root) {
        return TreeTraversal.postorderNonRecursive(root);

    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, null, 4, 5});
        postorderTraversal(root).forEach(System.out::println);
    }
}
