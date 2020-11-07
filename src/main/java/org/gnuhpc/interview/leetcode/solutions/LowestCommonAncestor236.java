package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeUtils;
import org.junit.Test;

import java.util.Deque;

import static org.gnuhpc.interview.datastructure.tree.basicimpl.TreeUtils.pathToX;

public class LowestCommonAncestor236 {
    @Test
    public void test() {
        Integer[] initArray = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        int p = 5, q = 1;

        TreeNode root = TreeCreator.createTreeByLevel(initArray);

        TreeNode tp = TreeUtils.findNode(root, p);
        TreeNode tq = TreeUtils.findNode(root, q);

        System.out.println(lowestCommonAncestor(root, tp, tq).val);

    }

    /*
    Method1 : 算出从root到val的节点的路径
     */
    // 使用这个可以求出两个叶子节点的距离，就是两个节点分别到最近公共祖先的距离
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pathToP = pathToX(root, p.val);
        Deque<TreeNode> pathToQ = pathToX(root, q.val);
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


    /*
    Method2 : 递归方法

     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        /*
        如果root是null，则说明我们已经找到最底了，返回null表示没找到
        如果root与p相等或者与q相等，则返回root
        如果左子树没找到，递归函数返回null，证明p和q同在root的右侧，那么最终的公共祖先就是右子树找到的结点
        如果右子树没找到，递归函数返回null，证明p和q同在root的左侧，那么最终的公共祖先就是左子树找到的结点
        */
        if (root == null || root == p || root == q)
            return root;

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode == null)
            return rightNode;
        if (rightNode == null)
            return leftNode;

        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p) return p;
        if(root == q) return q;

        int pPos = getBranch(root, p);
        int qPos = getBranch(root, q);
        if(pPos == qPos) {
            if(qPos == 1) return lowestCommonAncestor2(root.right, p, q);
            else return lowestCommonAncestor2(root.left, p, q);
        } else {
            return root;
        }
    }

    private int getBranch(TreeNode root, TreeNode n){
        if(isFound(root.left,n)) return 0;
        else return 1;
    }

    private boolean isFound(TreeNode root, TreeNode n){
        if(root == null) return false;
        if(root == n) return true;
        return isFound(root.left, n) || isFound(root.right, n);
    }

    /*
    Follow up: Given nodes in a binary tree, find the distance between them.
    TreeNode lca = lca(root, node1, node2);
    return getDistance(lca, node1) + getDistance(lca, node2);
     */

}
