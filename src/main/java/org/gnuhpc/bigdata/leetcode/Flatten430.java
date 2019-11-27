package org.gnuhpc.bigdata.leetcode;

public class Flatten430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    /*
       参考 114的思路22 中的解法，推荐这种，不用全局变量进行递归更好理解。
        */
    Node flatten1(Node head) {
        flattenTail(head);
        return head;
    }

    private Node flattenTail(Node root) {
        if(root == null) return null;
        Node leftTail = flattenTail(root.child);
        Node rightTail = flattenTail(root.next);
        if(root.child!=null) {//只有当左子树存在时才将它插入右子树中
            Node temp = root.next;
            root.next = root.child;
            root.child.prev = root;
            root.child = null;
            leftTail.next = temp;
            if (temp!=null) //注意root的next可能是null, 这里面要剔除这种情况
                temp.prev = leftTail;
        }

        //返回尾部元素时，需要特殊处理
        // (1) 有右子树的情况
        if(rightTail!=null) return rightTail;
        // (2) 无右子树但有左子树的情况
        if(leftTail!=null) return leftTail;
        // (3)左右子树均不存在的情况。
        return root;
    }

    /*
    Method2: preorder
    from https://blog.csdn.net/weixin_30507269/article/details/102004090
     */
    Node prev=null;

    Node flatten2(Node head) {
        if (head==null) return null;
        Node child=head.child, next=head.next;
        if (prev!=null){
            prev.next = head;
            head.prev = prev;
            prev.child = null;
        }
        prev = head;
        flatten2(child);
        flatten2(next);
        return head;
    }

    /**
     * 非递归, 一层层合并，最直观
     */
    public Node flatten3(Node head) {
        if( head == null) return head;
        // Pointer
        Node p = head;
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null )
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }
}
