package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeUtils;
import org.junit.Test;

import java.util.Stack;

public class LowestCommonAncestor236 {
    @Test
    public void test() {
        Integer[] initArray = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        int p = 5, q = 1;

        TreeNode root = TreeCreator.createTreeByLevel(initArray);

        TreeNode tp = TreeUtils.findNode(root,p);
        TreeNode tq = TreeUtils.findNode(root,q);

        System.out.println(lowestCommonAncestor(root, tp, tq).val);

    }

    /*
    Method1 : 算出从root到val的节点的路径
     */
    // 使用这个可以求出两个叶子节点的距离，就是两个节点分别到最近公共祖先的和
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pathToP = pathToX(root, p.val);
        Stack<TreeNode> pathToQ = pathToX(root, q.val);
        //If there is no p or q:
        if (pathToP == null || pathToQ == null) {
            return null;
        }

        TreeNode lcaToReturn = null;

        while (!pathToP.isEmpty() && !pathToQ.isEmpty()) {
            TreeNode pPop = pathToP.pop();
            TreeNode qPop = pathToQ.pop();
            if (pPop == qPop) {
                lcaToReturn = pPop;
            } else {
                break;
            }
        }
        return lcaToReturn;
    }



    public Stack<TreeNode> pathToX(TreeNode root, int x) {
        if (root == null) {
            return null;
        }

        if (root.val == x) {
            Stack<TreeNode> path = new Stack<>();
            path.push(root);
            return path;
        }

        Stack<TreeNode> leftPath = pathToX(root.left, x);
        if (leftPath != null) {
            leftPath.push(root);
            return leftPath;
        }

        Stack<TreeNode> rightPath = pathToX(root.right, x);
        if (rightPath != null) {
            rightPath.push(root);
            return rightPath;
        }

        return null;
    }

    /*
    Method2 : 递归方法
     */
    public TreeNode lowestCommonAncestorRecusive(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null||root==p||root==q){return root;}
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        return left==null?right:right==null?left:root;
    }
}
