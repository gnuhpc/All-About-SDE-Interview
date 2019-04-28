package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class MinDepth111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        if (root.left!=null && root.right != null)
            return Math.min(minDepth(root.right),minDepth(root.left))+1;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;

        return -1;
    }

    /**
     * add by Tina
     * 主义和求maxDepth的区别，考虑陷进出现的情况
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right != null){
            return 1+minDepth(root.right);
        }else if(root.left != null && root.right == null){
            return 1+minDepth(root.left);
        }else{
            return 1+Math.min(minDepth(root.right),minDepth(root.left));
        }
    }
}
