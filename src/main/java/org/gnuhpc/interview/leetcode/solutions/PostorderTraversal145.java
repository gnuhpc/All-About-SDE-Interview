package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeTraverse;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.List;

public class PostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        return TreeTraverse.postorder(root);
    }


    /*
    Method2: Stack 和前序遍历差不多
     */
    public List<Integer> postorderTraversalNonRecursive(TreeNode root) {
        return TreeTraverse.postorderNonRecursive(root);

    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, null, 4, 5});
        postorderTraversal(root).forEach(System.out::println);
    }
}
