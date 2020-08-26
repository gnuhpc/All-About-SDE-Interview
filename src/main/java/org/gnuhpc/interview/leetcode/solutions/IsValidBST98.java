package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.*;

public class IsValidBST98 {

    /*
    Method 1: 找最大值最小值都是递归了
     */
    public boolean isValidBST(TreeNode root) {
        return isBSTHelper(root, null, null);
    }

    public static boolean isBSTHelper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        if (lower != null && root.val <= lower) return false;
        if (upper != null && root.val >= upper) return false;
        return isBSTHelper(root.left, lower, root.val) && isBSTHelper(root.right, root.val, upper);
    }

    /*
       Method 2 : Inorder traversal
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || p != null) {
            //左子树
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            p = stack.pop();

            if (pre != null && p.val <= pre.val) return false;
            pre = p;

            p = p.right; //注意是中序遍历
        }

        return true;
    }

    /*
    Method3: add by Tina Inorder traverse and judge if in order
     */
    TreeNode prev = null;

    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST3(root.left)) return false;
        if (prev != null) {
            if (root.val <= prev.val) return false;
        }
        prev = root;
        return isValidBST3(root.right);
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5, 3, 6, 1, 4});
        System.out.println(isValidBST2(root));
        System.out.println(isValidBST3(root));
    }
}
