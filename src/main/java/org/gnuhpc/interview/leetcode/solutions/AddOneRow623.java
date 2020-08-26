package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/8/3
 */
public class AddOneRow623 {

    /* BFS
    层序遍历，当d=1时，直接构建新节点，返回新树，如果d>1，则进行层序遍历，当到达d-1层时记录父节点，记录完成后跳出层序遍历，按照题意构建新二叉树即可。

注意这里会出现d=树最大深度+1的情况，层序遍历取出父节点再操作可以避免遗漏这种情况。
链接：https://leetcode-cn.com/problems/add-one-row-to-tree/solution/java-ceng-xu-he-di-gui-liang-chong-jie-fa-by-zxy09/
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) return null;
        //如果需要创建根节点
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            //将原树添加到左子树
            newRoot.left = root;
            return newRoot;
        }
        //层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<TreeNode> parentList = new ArrayList<>();
        //记录层数
        int depth = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                //判断是否是d-1层，如果是，保存父节点
                if (depth == d - 1) {
                    parentList.add(cur);
                }
            }
            depth++;
            //到达d层，跳出层序遍历
            if (depth == d) {
                break;
            }
        }
        //对树进行重构
        for (TreeNode parent : parentList) {
            //获得原左右子树
            TreeNode left = parent.left;
            TreeNode right = parent.right;
            //构成新节点，拼接
            TreeNode newNodeLeft = new TreeNode(v);
            TreeNode newNodeRight = new TreeNode(v);
            newNodeLeft.left = left;
            newNodeRight.right = right;
            parent.left = newNodeLeft;
            parent.right = newNodeRight;
        }
        return root;
    }

    /*
    Method2: DFS
    1.当d==1时直接构建新节点，返回新树。

2.当d > 1时进行dfs，传入当前节点的深度，如果当前节点深度depth == d-1，则该节点为新节点的父节点，进行新子树构建

作者：mmmmmJCY
链接：https://leetcode-cn.com/problems/add-one-row-to-tree/solution/java-ceng-xu-he-di-gui-liang-chong-jie-fa-by-zxy09/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
    public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if (root == null) return null;
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            //将原树添加到左子树
            newRoot.left = root;
            return newRoot;
        }
        return helper(root, v, d, 1);
    }

    private TreeNode helper(TreeNode root, int v, int d, int depth) {
        if (root == null) return null;
        //到达d-1层，使用父节点构建新子树
        if (depth == d - 1) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            //构成新节点，拼接
            TreeNode newNodeLeft = new TreeNode(v);
            TreeNode newNodeRight = new TreeNode(v);
            newNodeLeft.left = left;
            newNodeRight.right = right;
            root.left = newNodeLeft;
            root.right = newNodeRight;
            return root;
        }
        root.left = helper(root.left, v, d, depth + 1);
        root.right = helper(root.right, v, d, depth + 1);
        return root;
    }
}
