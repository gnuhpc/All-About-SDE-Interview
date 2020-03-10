package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView199 {
    /*
    BFS
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);

                if (i == size - 1) res.add(node.val);
            }
        }

        return res;
    }

    /*
    DFS
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        if (curr.right != null) rightView(curr.right, result, currDepth + 1);
        if (curr.left != null) rightView(curr.left, result, currDepth + 1);
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, null, 5, null, 4});
        System.out.println(rightSideView(root));
    }
}
