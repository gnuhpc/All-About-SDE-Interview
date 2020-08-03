package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/Easy-to-understand-Java-Solution
//PreOrder
public class Codec297 {
    private static final String SPLITER = ",";
    private static final String NULL = "X";

    /*
    Method1 : BFS
     * BFS  采用层序遍历来实现序列化和反序列化
     * time : O(n);
     * space : O(n);
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(SPLITER).append("#").append(SPLITER);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                sb.append(node.left.val).append(SPLITER);
                q.add(node.left);
            } else {
                sb.append(NULL).append(SPLITER);
            }
            if (node.right != null) {
                sb.append(node.right.val).append(SPLITER);
                q.add(node.right);
            } else {
                sb.append(NULL).append(SPLITER);
            }

            sb.append("#").append(SPLITER);
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] s = data.split(SPLITER);

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        q.add(root);
        int i = 1;

        while (!q.isEmpty() && i < data.length()) {
            TreeNode node = q.poll();
            i++;//跳过#
            if (!s[i].equals(NULL)) {
                TreeNode left = new TreeNode(Integer.parseInt(s[i]));
                node.left = left;
                q.add(left);
            } else {
                node.left = null;
            }
            i++;//跳过

            if (!s[i].equals(NULL)) {
                TreeNode right = new TreeNode(Integer.parseInt(s[i]));
                node.right = right;
                q.add(right);
            } else {
                node.right = null;
            }
            i++;//跳过
        }

        return root;
    }


    /*
    Method2: DFS
     */
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serDFS(root, sb);
        return sb.toString();
    }

    private void serDFS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(SPLITER);
        } else {
            sb.append(node.val).append(SPLITER);
            serDFS(node.left, sb);
            serDFS(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        return deserDFS(data.split(SPLITER));
    }

    int pt = 0;

    private TreeNode deserDFS(String[] nodes) {
        String val = nodes[pt++];
        if (val.equals(NULL)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = deserDFS(nodes);
            node.right = deserDFS(nodes);
            return node;
        }
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5, 2, 3, null, null, 2, 4, 3, 1});

        System.out.println(serialize2(root));
        System.out.println(deserialize2(serialize2(root)));
    }
}
