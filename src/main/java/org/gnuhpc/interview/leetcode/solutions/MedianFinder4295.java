package org.gnuhpc.interview.leetcode.solutions;

//TODO 总结这种计数二叉树 295/10.10/480
public class MedianFinder4295 {
    private class TreeNode {
        int val;
        int size;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    private TreeNode root;

    /** initialize your data structure here. */
    public MedianFinder4295() {

    }

    public void addNum(int num) {
        if (this.root == null) {
            root = new TreeNode(num);
        } else {
            this.addNum(num, this.root);
        }
    }

    private void addNum(int num, TreeNode node) {
        if (node.val >= num) {
            if (node.left == null) {
                node.left = new TreeNode(num);
            } else {
                addNum(num, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(num);
            } else {
                addNum(num, node.right);
            }
        }
        node.size++;
    }

    public double findMedian() {
        if (this.root.size % 2 == 1) {
            return search(this.root.size / 2 + 1, this.root);
        } else {
            int left = search(this.root.size / 2, this.root);
            int right = search(this.root.size / 2 + 1, this.root);
            return (left + right) / 2.0;
        }
    }

    public int search(int index, TreeNode node) {
        if (node.size == 1) {
            return node.val;
        }
        int leftSize = node.left != null ? node.left.size : 0;
        if (leftSize >= index) {
            return search(index, node.left);
        } else if (leftSize + 1 == index) {
            return node.val;
        } else {
            return search(index - leftSize - 1, node.right);
        }
    }
}
