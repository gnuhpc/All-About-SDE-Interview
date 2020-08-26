package org.gnuhpc.interview.datastructure.tree.multitree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MultiTreeTraversal {
    public static MultiTreeNode makeTree() {
        MultiTreeNode root = new MultiTreeNode("A", null);

        MultiTreeNode B = new MultiTreeNode("B", root);
        MultiTreeNode C = new MultiTreeNode("C", root);
        MultiTreeNode D = new MultiTreeNode("D", root);

        root.addChild(B);
        root.addChild(C);
        root.addChild(D);


        MultiTreeNode E = new MultiTreeNode("E", B);
        MultiTreeNode F = new MultiTreeNode("F", B);

        B.addChild(E);
        B.addChild(F);

        MultiTreeNode G = new MultiTreeNode("G", D);

        D.addChild(G);


        MultiTreeNode H = new MultiTreeNode("H", E);
        MultiTreeNode I = new MultiTreeNode("I", E);
        MultiTreeNode J = new MultiTreeNode("J", E);

        E.addChild(H);
        E.addChild(I);
        E.addChild(J);

        return root;
    }

    /**
     * 递归遍历
     * 如果树太深的话，会出现java.lang.StackOverflowError
     *
     * @param root
     */
    public static void recursion(MultiTreeNode root) {
        System.out.println(root.getName());
        for (MultiTreeNode child : root.getChildren()) {
            recursion(child);
        }
    }

    /**
     * 广度优先需要构建一个先进先出的队列
     *
     * @param root
     */
    public static void breadthFirst(MultiTreeNode root) {
        Deque<MultiTreeNode> nodeDeque = new LinkedList<>();
        MultiTreeNode node = root;
        nodeDeque.add(node);
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            System.out.println(node.getName());
            nodeDeque.addAll(node.getChildren());
        }

    }

    /**
     * 深度优先需要构建一个后进先出的栈
     *
     * @param root
     */
    public static void depthFirst(MultiTreeNode root) {
        Deque<MultiTreeNode> nodeDeque = new LinkedList<>();
        MultiTreeNode node = root;
        nodeDeque.push(node);
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.pop();
            System.out.println(node.getName());
            List<MultiTreeNode> children = node.getChildren();
            //注意这里要从后向前遍历
            for (int i = children.size() - 1; i >= 0; i--) {
                nodeDeque.push(children.get(i));
            }
        }
    }

    public static void main(String[] args) {
        MultiTreeNode root = makeTree();
        recursion(root);
        breadthFirst(root);
        depthFirst(root);
    }
}
