package org.gnuhpc.interview.leetcode.solutions;

import com.mysql.cj.jdbc.NonRegisteringDriver;
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

    @Test
    public void test() {
        //recoverFromPreorder("1-2--3--4-5--6--7");
        recoverFromPreorder("1-2--3---4-5--6---7");
    }
}
