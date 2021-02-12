package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

public class TreeBuilder1628 {
    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    };
    Node buildTree(String[] postfix) {
        Deque<BiOpNode> stack = new LinkedList<>();
        for (String s : postfix) {
            BiOpNode node = new BiOpNode(s);
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                node.right = stack.pop();
                node.left = stack.pop();
            }
            stack.push(node);
        }
        return stack.pop();
    }

    class BiOpNode extends Node {
        String val;
        BiOpNode left;
        BiOpNode right;

        BiOpNode(String val) {
            this.val = val;
        }

        @Override
        public int evaluate() {
            switch (val) {
                case "+":
                    return left.evaluate() + right.evaluate();
                case "-":
                    return left.evaluate() - right.evaluate();
                case"*":
                    return left.evaluate() * right.evaluate();
                case"/":
                    return left.evaluate() / right.evaluate();
                default:
                    return Integer.valueOf(val);
            }
        }
    }
}
