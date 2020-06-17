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
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append(NULL).append(SPLITER);
            } else {
                sb.append(cur.val).append(SPLITER);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] str = data.split(SPLITER);
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < str.length) {
            TreeNode cur = queue.poll();
            if (!str[i].equals(NULL)) {
                cur.left = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(cur.left);
            } else {
                cur.left = null;
            }

            i++;
            if (i == str.length) return root;

            if (!str[i].equals(NULL)) {
                cur.right = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(cur.right);
            } else {
                cur.right = null;
            }

            i++;
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
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, 4, 5});

        System.out.println(serialize2(root));
        System.out.println(deserialize2(serialize2(root)));
    }
}
