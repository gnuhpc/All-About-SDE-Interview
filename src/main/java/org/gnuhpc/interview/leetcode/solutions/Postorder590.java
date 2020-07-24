package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

public class Postorder590 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        dfs(root,res);
        return res;
    }

    private void dfs(Node root, List<Integer> res){
        if(root == null) {
            return;
        }



        for(Node child: root.children){
            dfs(child,res);
        }

        res.add(root.val);

        return;
    }
}
