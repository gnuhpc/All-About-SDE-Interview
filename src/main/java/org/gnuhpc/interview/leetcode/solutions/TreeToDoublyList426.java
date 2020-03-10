package org.gnuhpc.interview.leetcode.solutions;


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
        Node dummy = pre;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //注意下边这三行和后边递归方法的是一样的
            pre.right = root;
            root.left = pre;
            pre = root;
            root = root.right;//别忘了转向右子树
        }
        //最后处理头尾
        pre.right = dummy.right;
        dummy.right.left = pre;

        return dummy.right;
    }


    public Node treeToDoublyList3(Node root) {
        if (root == null) {
            return null;
        }
        Node head = root;
        while (head.left != null) head = head.left;
        helper(root);
        //connect tail with head;
        prev.right = head;
        head.left = prev;
        return head;
    }

    Node prev = null; //怎么在DFS中记录上一个元素

    private void helper(Node root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (prev != null) {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        helper(root.right);
    }
    /*
       Method3 : 递归 inplace
        */

    public Node treeToDoublyList4(Node root) {
        if (root == null) return null;
        Node left = treeToDoublyList3(root.left);
        Node right = treeToDoublyList3(root.right);
        root.left = root;
        root.right = root;
        return join(join(left, root), right);
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
