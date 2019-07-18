package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RecoverTree99 {
    /*
    Method 1 Inorder
     */
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);

        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the find (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right);
    }

    /*
    Method 2: Inorder
     */
    public void recoverTree2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);

        TreeNode pre = null,post = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                pre = list.get(i);
                break;
            }
        }

        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).val < list.get(i - 1).val) {
                post = list.get(i);
                break;
            }
        }

        replaceValue(root, pre, post);

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

    private void replaceValue(TreeNode tree, TreeNode n1, TreeNode n2) {

        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}
