package org.gnuhpc.bigdata.leetcode;


import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

/**
 * Copyright gnuhpc 19-7-24
 */
//左中右是按从小到大的遍历，所以右中左遍历就是从大到小，因此右中左遍历，比例过程中不断的加上原来的数就行了。
public class ConvertBST538 {
    //use a integer to cache plus result
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        return root;
    }

    //reverse inOrder to traverse root,
    //plus cache result to current node val and set current node val to cache result
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.right);
        root.val += sum;
        sum = root.val;
        inOrder(root.left);
    }

}

