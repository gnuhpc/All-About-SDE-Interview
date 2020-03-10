package org.gnuhpc.interview.datastructure.tree.basicimpl;

import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeCreator {
    // Sample function for creating a tree.
    // Input:
    // - mapping: a node-to-node mapping that shows how the tree should be constructed
    // - headValue: the val that will be used for the head ndoe
    // Output:
    // - The head node of the resulting tree
    public static TreeNode createTree(HashMap<Integer, int[]> mapping, int headValue) {
        TreeNode head = new TreeNode(headValue);
        HashMap<Integer, TreeNode> nodes = new HashMap<>();
        nodes.put(headValue, head);
        for (int key : mapping.keySet()) {
            int[] value = mapping.get(key);
            TreeNode leftChild = new TreeNode(value[0]);
            TreeNode rightChild = new TreeNode(value[1]);
            nodes.put(value[0], leftChild);
            nodes.put(value[1], rightChild);
        }
        for (int key : mapping.keySet()) {
            int[] value = mapping.get(key);
            nodes.get(key).left = nodes.get(value[0]);
            nodes.get(key).right = nodes.get(value[1]);
        }
        return head;
    }

    public static TreeNode createTreeByLevel(Integer[] initArray) {
        TreeNode head = new TreeNode(initArray[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(head);

        for (int i = 0; !q.isEmpty() && i < initArray.length; i++) {
            TreeNode cur = q.poll();
            if (2 * i + 1 < initArray.length && initArray[2 * i + 1] != null) {
                cur.left = new TreeNode(initArray[2 * i + 1]);
                q.offer(cur.left);
            }

            if (2 * i + 2 < initArray.length && initArray[2 * i + 2] != null) {
                cur.right = new TreeNode(initArray[2 * i + 2]);
                q.offer(cur.right);
            }
        }

        return head;
    }


//     NOTE: The following input profits will be used for testing your solution.
//     The mapping we're going to use for constructing a tree.
//     For example, {0: [1, 2]} means that 0's left child is 1, and its right
//     child is 2.

//    HashMap<Integer, int[]> mapping1 = new HashMap<Integer, int[]>();
//    int[] childrenA = {1, 2};
//    int[] childrenB = {3, 4};
//    int[] childrenC = {5, 6};
//    mapping1.put(0,childrenA);
//    mapping1.put(1,childrenB);
//    mapping1.put(2,childrenC);
//
//    TreeNode head1 = createTree(mapping1, 0)
//                  1
//               2     3
//            4    5  6
//               7

    public TreeNode createTree(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return null;
        }

        int rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        TreeNode root = new TreeNode(rootValue);
        root.left = createTree(
                preOrder.substring(1, 1 + rootIndex),
                inOrder.substring(0, rootIndex));
        root.right = createTree(
                preOrder.substring(1 + rootIndex),
                inOrder.substring(1 + rootIndex));

        return root;
    }

    public TreeNode createBST(int[] nums) {
        TreeNode root = null;
        for (int n : nums) {
            root = addBSTNode(root, n);
        }

        return root;
    }

    private TreeNode addBSTNode(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            if (val <= root.val) {
                root.left = addBSTNode(root.left, val);
            } else {
                root.right = addBSTNode(root.right, val);
            }
        }

        return root;
    }

    @Test
    public void test() {
        TreeNode root = createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        TreeNode bstRoot = createBST(new int[]{5, 1, 6, 3});


        System.out.println();
    }
}
