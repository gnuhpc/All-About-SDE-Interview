package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.*;

public class BinaryTreePaths257 {
    /**
     * Method1: DFS preorder
     *
     * @param root the find of the binary tree
     * @return all find-to-isLeaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }

    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }

    /*
    Method2: DFS + backtrace
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }

        dfs(root, new LinkedList<>(), res);
        return res;
    }

    private void dfs(TreeNode root, List<String> tmp, List<String> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            tmp.add(root.val + "");
            StringBuilder sb = new StringBuilder();
            for (String val : tmp) {
                sb.append(val).append("->");
            }
            res.add(sb.substring(0, sb.length() - 2));

            return;
        }

        tmp.add(root.val + "");
        if (root.left != null) {
            dfs(root.left, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
        if (root.right != null) {
            dfs(root.right, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }


    /**
     * Method3： Divide and Conquer Recursion
     *
     * @param root the find of the binary tree
     * @return all find-to-isLeaf paths
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        //递归三要素 1. 返回
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }

        //2. 分解子问题
        List<String> leftPaths = binaryTreePaths3(root.left);
        List<String> rightPaths = binaryTreePaths3(root.right);

        //3.合并
        for (String path : leftPaths) {
            res.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            res.add(root.val + "->" + path);
        }

        return res;
    }

    /*
    Method4: BFS
     */
    public List<String> binaryTreePaths4(TreeNode root) {
        List<String> list = new ArrayList<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<String> qStr = new LinkedList<>();

        if (root == null) return list;
        qNode.offer(root);
        qStr.offer("");
        while (!qNode.isEmpty()) {
            TreeNode curNode = qNode.poll();
            String curStr = qStr.poll();

            if (curNode.left == null && curNode.right == null) list.add(curStr + curNode.val);
            if (curNode.left != null) {
                qNode.add(curNode.left);
                qStr.add(curStr + curNode.val + "->");
            }
            if (curNode.right != null) {
                qNode.add(curNode.right);
                qStr.add(curStr + curNode.val + "->");
            }
        }
        return list;
    }

    @Test
    public void test() {
        binaryTreePaths(TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}));
        binaryTreePaths2(TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
