package org.gnuhpc.bigdata.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


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
    };

    /*
    Method1 : DFS递归
     */
    /*
    深度优先搜索 就像一棵树（二叉树）的前序遍历，从某个顶点（链表头节点）出发，自顶向下遍历，然后遇到顶点的未被访问的邻接点（子节点 Child），继续进行深度优先遍历，重复上述过程（递归），直到所有顶点都被访问为止。

其逻辑以示例输入为例：

 1---2---3---4---5---6--null
       |
        7---8---9---10--null
              |
             11---12---null
从节点 1 开始遍历，当前遍历链表为：1---2---3---4---5---6--null

遇到邻接点 2，其子链表为：7---8---9---10--null
将子链表头节点 7 作为参数传入 DFS 函数，当前遍历链表为：7---8---9---10---null

继续遍历，遇到邻接点 8，其子链表为：11--12--null
将子链表头节点 8 作为参数传入 DFS 函数，当前遍历链表为：11--12---null

继续遍历，无邻接点，遍历结束，返回当前链表尾节点 12
改变邻接点 8 与子链表头节点 11 关系：7---8---11---12
连接返回值 尾节点 12 和邻接点的下一个节点 9： 7---8---11---12---9---10---null

继续遍历，无邻接点，遍历结束，返回当前链表尾节点 10
改变邻接点 2 与 子链表头节点 7 关系：1---2---7---8---11---12---9---10--null
连接返回值 尾节点 10 和邻接点的下一个节点 3： 1---2---7---8---11---12---9---10---3---4---5---6--null

继续遍历，无邻接点，遍历结束，返回当前链表尾节点 6

递归结束，返回头节点 1，链表为： 1---2---7---8---11---12---9---10---3---4---5---6--null
     */
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }
    //深度优先搜索函数
    private Node dfs(Node head) { //return tail TODO
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                //改变当前节点与子节点的关系
                Node next = cur.next;//记录暂存下一个节点
                cur.next = cur.child;//当前节点与子链表头节点连接
                cur.next.prev = cur;
                //传递子链表头节点作为参数到 dfs
                Node childLast = dfs(cur.child);//childLast获得返回值为子链表的尾节点
                childLast.next = next;//子链表尾节点与暂存节点连接
                if (next != null) next.prev = childLast;//暂存节点不为空就将其prev指向子链表尾节点
                cur.child = null;//子链表置空
                cur = childLast;//刷新当前节点，跳过子链表的遍历
            }
            head = cur;//头节点刷新为当前节点
            cur = cur.next;//刷新当前节点
        }
        return head;//返回子链表的尾节点
    }

    /*
    Method2: preorder TODO 注意看一下114的方法2
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

    /*
    参考 114的思路22 中的解法，推荐这种，不用全局变量进行递归更好理解。
     */
    Node flatten22(Node head) {
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

    /**
     * 非递归
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
