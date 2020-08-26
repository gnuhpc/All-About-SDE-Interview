package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.Arrays;

public class ConstructMaximumBinaryTree654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int maxIdx = findMaxIdx(nums);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, maxIdx));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, maxIdx + 1, nums.length));

        return root;

    }

    private int findMaxIdx(int[] nums) {

        int maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    @Test
    public void test() {

        TreeNode res = constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        return;
    }
}
