package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

public class Tree2str606 {
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        if(t.left == null && t.right == null) return t.val+"";
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        sb.append("(");
        sb.append(tree2str(t.left));
        sb.append(")");
        String right = tree2str(t.right);
        if(right!=""){
            sb.append("(");
            sb.append(right);
            sb.append(")");
        }

        return sb.toString();
    }

}
