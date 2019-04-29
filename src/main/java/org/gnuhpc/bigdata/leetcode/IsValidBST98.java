package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.*;

public class IsValidBST98 {
    /*
    Method 1: 判断以root为根的左右是否为BST，然后递归判断
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && findMax(root.left) >= root.val)     return false;
        if (root.right != null && findMin(root.right) <= root.val)   return false;

        return isValidBST(root.right) && isValidBST(root.left);
    }

    public int findMax(TreeNode root) {
        int max = Integer.MIN_VALUE;
        while (root != null) {
            max = Math.max(max, root.val);
            root = root.right;
        }
        return max;
    }

    public int findMin(TreeNode root) {
        int min = Integer.MAX_VALUE;
        while (root != null) {
            min = Math.min(min, root.val);
            root = root.left;
        }
        return min;
    }

    /*
    Method 2: 和方法一一样，连找最大值最小值都是递归了
     */
    public boolean isValidBST2(TreeNode root) {
        return isBstHelper(root, null, null);
    }

    public static boolean isBstHelper(TreeNode root, Integer lowerLim, Integer upperLim) {
        if (root==null) return true;
        if (lowerLim != null && root.val <= lowerLim) return false;
        if (upperLim != null && root.val >= upperLim) return false;
        return isBstHelper(root.left, lowerLim, root.val) && isBstHelper(root.right, root.val, upperLim);
    }

    /*
       Method 3 : Inorder traversal
     */
    public boolean isValidBST3(TreeNode root){
        if (root == null){
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        TreeNode pre = null;
        while(!stack.isEmpty() || p != null) {
            //左子树
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (pre!=null && p.val <= pre.val) return false;
                pre = p;
                p = p.right;
            }
        }

        return true;
    }

    /*
    Method4 :采用遍历法（Traversal） 时间复杂度 O(n)O(n)
     */

    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    public boolean isValidBST4(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST4(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST4(root.right)) {
            return false;
        }
        return true;
    }

    /*
    Method5 :分治
     */

    class ResultType {
        public boolean isBST;
        public TreeNode maxNode, minNode;
        public ResultType(boolean isBST) {
            this.isBST = isBST;
            this.maxNode = null;
            this.minNode = null;
        }
    }

    public boolean isValidBST5(TreeNode root) {
        return divideConquer(root).isBST;
    }

    private ResultType divideConquer(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }

        ResultType left = divideConquer(root.left);
        ResultType right = divideConquer(root.right);
        if (!left.isBST || !right.isBST) {
            return new ResultType(false);
        }

        if (left.maxNode != null && left.maxNode.val >= root.val) {
            return new ResultType(false);
        }

        if (right.minNode != null && right.minNode.val <= root.val) {
            return new ResultType(false);
        }

        // is bst
        ResultType result = new ResultType(true);
        result.minNode = left.minNode != null ? left.minNode : root;
        result.maxNode = right.maxNode != null ? right.maxNode : root;

        return result;
    }
    @Test
    public void test(){
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5,3,6,1,4});
        System.out.println(isValidBST2(root));
        System.out.println(isValidBST3(root));
    }

    // add by Tina Inorder traverse and judge if in order
    long last = Long.MIN_VALUE;
    public boolean isValidBST6(TreeNode root) {
        if (root == null) return true;
        if ( !isValidBST(root.left) ) return false;
        if (root.val <= last) return false;
        last = root.val;
        return isValidBST(root.right) ;
    }
}
