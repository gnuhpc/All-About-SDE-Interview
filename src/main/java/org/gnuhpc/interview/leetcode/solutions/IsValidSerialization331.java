package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Stack;

public class IsValidSerialization331 {
    /*
    Understand the problem:
    The key of the problem is if a preorder traversal of a binary tree is valid,
    a leaf node must have the sequence like "number, #, #".
    Therefore, we can start from leaf nodes of tree, remove the leaf nodes by replacing the number, #, # by a single # until the tree becomes empty.
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }

        String[] nodes = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (String node : nodes) {
            if (node.equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }

                    stack.pop();
                }
            }

            stack.push(node);
        }

        return stack.size() == 1 && stack.peek().equals("#");
    }

    @Test
    public void test() {
        System.out.println(isValidSerialization("1,#"));
        System.out.println(isValidSerialization("9,#,#,1"));
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(isValidSerialization("1,#,#,#,#"));
    }
}
