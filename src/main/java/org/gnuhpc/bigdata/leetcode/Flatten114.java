package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Flatten114 {
    /*
    Method1 : preorder 递归 前序遍历放在一个数组里， 最无脑也是有一定额外空间的
     */
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i).left = null;
        }

    }

    public void preorder(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    /*
    Method2 : preorder TODO
     */

    /*
    根据题目样例，可以看出生成链表的节点顺序为树前序遍历的顺序。因此，我们思路是对树执行前序遍历，并修改每个节点的指针指向。
我们使用递归方法前序遍历，其中：
终止条件：越过叶子节点，即root == null，直接返回；
指针修改：
用一个全局变量pre保存上一个节点：将pre右子树指针指向当前节点root；将pre左子树指针清空。
进入下轮递归前，将当前节点root赋给pre；
由于在flatten(root.left)方法执行后执行flatten(root.right)，但root.right指向节点可能已经改变，造成错误的递归顺序，因此需要在执行此方法前存储root.right至right变量，用此变量做每个节点的右子树递归。
本题无需返回值，时空间复杂度均为O(N)。

作者：jyd
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/flatten-binary-tree-to-linked-list-qian-xu-bian-li/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    private TreeNode pre;

    public void flatten2(TreeNode root) {
        if (root == null) return;
        //如果pre不是null先进行连接
        if (pre != null) {
            pre.right = root;
            pre.left = null;
        }

        //不管是不是连接pre都指向root， 走向tail
        pre = root;

        TreeNode left = root.left;
        TreeNode right = root.right;
        flatten2(left);
        flatten2(right);
    }

    /*
    Method22: 和上一种方法基本一致，返回尾部
     */
    /*
    假设某节点的左右子树T(root->left)和T(root->right)已经flatten成linked list了：

        1
      /   \
     2     5
      \     \
       3     6 <- rightTail
        \
         4 <- leftTail

    如何将root、T(root->left)、T(root->right) flatten成一整个linked list？显而易见：

    temp = root->right
    root->right  = root->left
    root->left = NULL
    leftTail->right = temp
     */
    public void flatten22(TreeNode root) {
        flattenTail(root);
    }

    private TreeNode flattenTail(TreeNode root) {
        if (root == null) return null;
        TreeNode leftTail = flattenTail(root.left);
        TreeNode rightTail = flattenTail(root.right);
        if (root.left != null) {//只有当左子树存在时才将它插入右子树中
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;
            leftTail.right = temp;
        }

        //返回尾部元素时，需要特殊处理
        // (1) 有右子树的情况
        if (rightTail != null) return rightTail;
        // (2) 无右子树但有左子树的情况
        if (leftTail != null) return leftTail;
        // (3)左右子树均不存在的情况。
        return root;
    }

    /*
    Method3 : 递归,递归的时候求出左节点的最右孩子即可
     */

    public void flatten3(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten3(left);
        flatten3(right);

        //root的右边节点连接被压平的左边坐标
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

    /*
    Method4: preorder非递归
     */

    public void flatten4(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            // connect
            node.left = null;
            if (stack.isEmpty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }


}
