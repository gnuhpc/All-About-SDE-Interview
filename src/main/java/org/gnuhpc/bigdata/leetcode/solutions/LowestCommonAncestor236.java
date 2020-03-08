package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeUtils;
import org.junit.Test;

import java.util.Stack;

import static org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeUtils.pathToX;

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
            }
            else {
                break;
            }
        }
        return lcaToReturn;
    }


    /*
    Method2 : 递归方法

     */
    public TreeNode lowestCommonAncestorRecusive(TreeNode root, TreeNode p, TreeNode q) {
        //如果找到 p 或者 q 那么就没有必要接着递归，因为共同祖先只可能是该节点或该节点祖先
        //如果 find 为空了，说明这条路径上不可能有 p 或 q 节点，返回空
        if (root == null || root == p || root == q) {return root;}
        TreeNode left = lowestCommonAncestor(root.left, p, q);//往左分支上寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q); //往右分支上寻找
        return left == null ? right : right == null ? left : root;
    }

    /*
    Follow up: Given nodes in a binary tree, find the distance between them.
    TreeNode lca = lca(root, node1, node2);
    return getDistance(lca, node1) + getDistance(lca, node2);
     */

}
