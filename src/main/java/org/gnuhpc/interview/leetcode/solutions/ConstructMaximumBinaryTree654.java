package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.Arrays;

public class ConstructMaximumBinaryTree654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        if (nums.length == 1) return new TreeNode(nums[0]);

        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) return null;
        if (end - start == 0) return new TreeNode(nums[start]);
        int maxIdx = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = helper(nums, start, maxIdx - 1);
        root.right = helper(nums, maxIdx + 1, end);

        return root;
    }


    @Test
    public void test() {

        TreeNode res = constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        return;
    }
}
