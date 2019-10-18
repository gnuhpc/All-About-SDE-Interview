package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.junit.Test;

import java.util.*;

public class BinaryTreePaths257 {
    /** Method1: DFS
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
    Method2: DFS 非递归
     */
    public List binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<>();
        Stack<TreeNode> sNode = new Stack<>();
        Stack<String> sStr = new Stack<>();

        if (root == null) return list;
        sNode.push(root);
        sStr.push("");
        while (!sNode.isEmpty()) {
            TreeNode curNode = sNode.pop();
            String curStr = sStr.pop();

            if (curNode.left == null && curNode.right == null) list.add(curStr + curNode.val);
            if (curNode.left != null) {
                sNode.push(curNode.left);
                sStr.push(curStr + curNode.val + "->");
            }
            if (curNode.right != null) {
                sNode.push(curNode.right);
                sStr.push(curStr + curNode.val + "->");
            }
        }
        return list;
    }


    /**
     * Method3： Divide and Conquer Recursion
     * @param root the find of the binary tree
     * @return all find-to-isLeaf paths
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        //递归三要素 1. 返回
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        if (root.left == null && root.right == null) {
            paths.add(root.val + "");
            return paths;
        }

        //2. 分解子问题
        List<String> leftPaths = binaryTreePaths2(root.left);
        List<String> rightPaths = binaryTreePaths2(root.right);

        //3.合并
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }

        return paths;
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
