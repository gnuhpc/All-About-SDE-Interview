package org.gnuhpc.bigdata.leetcode;


import java.util.ArrayList;
import java.util.List;

public class TreeToDoublyList426 {
    /*
    画图就可以得到
     */
    public Node treeToDoublyList(Node root) {
        if (root.left == null && root.right == null){
            root.left = root;
            root.right = root;
            return root;
        }

        List<Node> l = inorder(root);

        Node dummy = new Node();
        Node cur = dummy;
        for (Node n: l){
            cur.right = n;
            n.left = cur;
            cur = cur.right;
        }

        cur.right = dummy.right;
        dummy.right.left = cur;

        return dummy.right;
    }

    private List<Node> inorder(Node root){
        List<Node> list = new ArrayList<>();
        visit(list,root);
        return list;
    }

    private void visit(List<Node> list, Node node) {
        if (node == null) return;
        visit(list,node.left);
        list.add(node);
        visit(list,node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
