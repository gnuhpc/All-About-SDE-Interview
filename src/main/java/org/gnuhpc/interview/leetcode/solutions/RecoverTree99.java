package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RecoverTree99 {
    /*
    中序遍历顺序是 4,2,3,1,我们只要找到节点4和节点1交换顺序即可!
这里我们有个规律发现这两个节点:
第一个节点,是第一个按照中序遍历时候前一个节点大于后一个节点,我们选取前一个节点,这里指节点4;
第二个节点,是在第一个节点找到之后, 后面出现前一个节点大于后一个节点,我们选择后一个节点,这里指节点1;
作者：powcai
链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/zhong-xu-bian-li-by-powcai/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /*
    Method 1 Inorder
     */
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (firstNode == null && pre.val > p.val) firstNode = pre;
            if (firstNode != null && pre.val > p.val) secondNode = p;
            pre = p;
            p = p.right;
        }

        replaceValue(firstNode, secondNode);
    }

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree2(TreeNode root) {
        inOrder(root);
        replaceValue(firstNode, secondNode);
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (firstNode == null && preNode.val > root.val) firstNode = preNode;
        if (firstNode != null && preNode.val > root.val) secondNode = root;
        preNode = root;
        inOrder(root.right);
    }


    /*
    Method 2: Inorder + O(n) space
     */
    public void recoverTree3(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);

        TreeNode firstNode = null, secondNode = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                firstNode = list.get(i);
                break;
            }
        }

        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).val < list.get(i - 1).val) {
                secondNode = list.get(i);
                break;
            }
        }

        replaceValue(firstNode, secondNode);

    }

    private void inOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        } else {
            inOrder(root.left, list);
            list.add(root);
            inOrder(root.right, list);
        }
    }

    private void replaceValue(TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}
