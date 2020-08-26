package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftValue513 {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int res = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0;i<size;i++){
                TreeNode n = q.poll();

                if(i==0) res = n.val;

                if(n.left !=null) q.offer(n.left);
                if(n.right !=null) q.offer(n.right);
            }
        }

        return res;
    }
}
