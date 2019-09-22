package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

public class MaxPathSum124 {
    public int maxPathSum(TreeNode root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(TreeNode root, int[] max) {
        if (root == null)
            return 0;

        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);

        int current = Math.max(root.val, Math.max(root.val + left, root.val + right));
        max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

        //注意返回的只是必须包含root.val的结果，而真正的max是被记录但是不被返回的。
        return current;
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{-1, null, 2});
        System.out.println(maxPathSum(root));
    }

}
