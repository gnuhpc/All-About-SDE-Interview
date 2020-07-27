package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

public class Preorder589 {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        dfs(root,res);
        return res;
    }

    private void dfs(Node root, List<Integer> res){
        if(root == null) {
            return;
        }

        res.add(root.val);

        for(Node child: root.children){
            dfs(child,res);
        }

        return;
    }
}
