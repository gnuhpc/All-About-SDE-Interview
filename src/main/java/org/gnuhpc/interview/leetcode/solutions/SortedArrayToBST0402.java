package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class SortedArrayToBST0402 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        if (nums.length == 1) return new TreeNode(nums[0]);

        TreeNode root = new TreeNode(nums[nums.length / 2]);

        int[] left = Arrays.copyOfRange(
                nums, 0, nums.length / 2);

        int[] right = Arrays.copyOfRange(
                nums, nums.length / 2 + 1, nums.length);

        root.left = sortedArrayToBST(left);
        root.right = sortedArrayToBST(right);

        return root;
    }
    /*
        思路一致，但是不用copy数组了
     */

    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);
        return node;
    }

    @Test
    public void test() {
        sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }
}
