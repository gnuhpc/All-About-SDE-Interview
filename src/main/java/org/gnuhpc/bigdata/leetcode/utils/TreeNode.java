package org.gnuhpc.bigdata.leetcode.utils;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }


    /**
     *           5
     *        2     7
     *      1  3   6  9
     *          4
     */
    public static TreeNode sampleBST(){
        Map<Integer, TreeNode> nodes = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            nodes.put(i, new TreeNode(i));
        }
        nodes.get(5).left = nodes.get(2);
        nodes.get(5).right = nodes.get(7);
        nodes.get(2).left = nodes.get(1);
        nodes.get(2).right = nodes.get(3);
        nodes.get(3).right = nodes.get(4);
        nodes.get(7).left = nodes.get(6);
        nodes.get(7).right = nodes.get(9);
        return nodes.get(5);
    }

    public static TreeNode buildBST(int[] array){
        TreeNode root = null;
        for(int i = 0; i < array.length; i++){
            root = insert(root, array[i]);
        }
        return root;
    }

    private static TreeNode insert(TreeNode node, int element){
        if(node == null) return new TreeNode(element);
        if(element < node.val) node.left = insert(node.left, element);
        else node.right = insert(node.right, element);
        return node;
    }
}
