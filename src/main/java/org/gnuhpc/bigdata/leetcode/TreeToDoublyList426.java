package org.gnuhpc.bigdata.leetcode;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeToDoublyList426 {
    /*
    画图就可以得到
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            root.left = root;
            root.right = root;
            return root;
        }

        List<Node> l = inorder(root);

        Node dummy = new Node();
        Node cur = dummy;
        for (Node n : l) {
            cur.right = n;
            n.left = cur;
            cur = cur.right;
        }

        cur.right = dummy.right;
        dummy.right.left = cur;

        return dummy.right;
    }

    private List<Node> inorder(Node root) {
        List<Node> list = new ArrayList<>();
        visit(list, root);
        return list;
    }

    private void visit(List<Node> list, Node node) {
        if (node == null) return;
        visit(list, node.left);
        list.add(node);
        visit(list, node.right);
    }

    /*
    Method2 : in-place 非递归
     */

    public Node treeToDoublyList2(Node root) {
        if (root == null)
            return root;

        Deque<Node> stack = new LinkedList<>();
        Node pre = new Node(0, null, null);
        Node res = pre;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                pre.right = root;
                root.left = pre;
                pre = root;
                root = root.right;
            }
        }
        //最后处理头尾
        pre.right = res.right;
        res.right.left = pre;

        return res.right;
    }

    /*
    Method3 : 递归 inplace
     */
    public Node treeToDoublyList3(Node root) {
        if (root == null) return null;
        Node left = treeToDoublyList3(root.left);
        Node right = treeToDoublyList3(root.right);
        root.left = root;
        root.right = root;
        return join( join(left, root), right );
    }
    private Node join(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        Node lastLeft = left.left;
        Node lastRight = right.left;

        lastLeft.right = right;
        right.left = lastLeft;
        lastRight.right = left;
        left.left = lastRight;

        return left;
    }

    Node prev = null; //TODO 怎么在DFS中记录上一个元素
    public Node treeToDoublyList4(Node root) {
        if (root == null){
            return null;
        }
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        //connect tail with head;
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper(Node cur){
        if (cur == null){
            return;
        }
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
