package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

/**
 * Copyright gnuhpc 2020/3/1
 */
/*
1. The first number is always root.
2. The string after root is always equal to n dashes, where n is next level.
3. If the right node is null, we can only find one string of n dashes.
Ex: "1-2--3--4-5--6--7"

" 1  -  (2--3--4)  -  (5--6--7) "
 root  left string  right string

1 is always the value of root.
"-" is always the string after root.

4. Do it recursively.
 */

public class RecoverFromPreorder1028 {

    public TreeNode recoverFromPreorder(String S) {
        return helper(1, S);
    }

    private TreeNode helper(int level, String S) {
        // find root number
        int i = 0;
        while (i < S.length() && S.charAt(i) != '-') i++;
        TreeNode node = new TreeNode(new Integer(S.substring(0, i)));
        if (i == S.length()) return node;
        // find left & right nodes
        S = S.substring(i + level);
        i = 0;
        while (i < S.length()) {
            int cnt = 0;
            while (i < S.length() && S.charAt(i) == '-') {
                i++;
                cnt++;
            }
            // right node exists
            if (cnt == level) {
                node.left = helper(level + 1, S.substring(0, i - level));
                node.right = helper(level + 1, S.substring(i));
                return node;
            }
            //Skip all the non dash character
            while (i < S.length() && S.charAt(i) != '-') i++;
        }
        // only left node
        node.left = helper(level + 1, S);
        return node;
    }

    // 遍历S的全局指针
    private int pt = 0;

    public TreeNode recoverFromPreorder2(String S) {
        if (S.isEmpty()) return null;
        return buildBinaryTree(S.toCharArray(), 0);
    }

    public TreeNode buildBinaryTree(char[] ss, int depth) {

        // 判定当前节点是否是null
        if (pt + depth >= ss.length || isNullPointer(ss, depth)) return null;

        // index指针跳过depth个'-'，指向下一个节点的开始位置
        pt += depth;

        // 左右子树递归
        TreeNode root = new TreeNode(getValue(ss));
        root.left = buildBinaryTree(ss, depth + 1);
        root.right = buildBinaryTree(ss, depth + 1);

        // 返回当前节点
        return root;
    }


    // 获取当前节点的val值，由于可能有多位，需要遍历一下
    public int getValue(char[] ss) {
        int value = 0;
        while (pt < ss.length && ss[pt] != '-') {
            value = value * 10 + (ss[pt] - '0');
            pt++;
        }
        return value;
    }

    // 判断当前位置的节点是不是null
    public boolean isNullPointer(char[] ss, int depth) {
        for (int i = pt; i < pt + depth; i ++) {
            if (ss[i] != '-') return true;
        }
        return false;
    }

    @Test
    public void test() {
        //recoverFromPreorder("1-2--3--4-5--6--7");
        recoverFromPreorder("1-2--3---4-5--6---7");
    }
}
