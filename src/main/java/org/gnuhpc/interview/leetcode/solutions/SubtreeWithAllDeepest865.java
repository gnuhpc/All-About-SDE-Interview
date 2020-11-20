package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class SubtreeWithAllDeepest865 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root==null)
            return null;
        int ld=depth(root.left);
        int rd=depth(root.right);
        if(ld==rd){
            return root;
        }
        else if(ld>rd){
            return lcaDeepestLeaves(root.left);
        }
        else {
            return lcaDeepestLeaves(root.right);
        }
    }
    public int depth(TreeNode node){
        if(node==null)
            return 0;
        int left=depth(node.right);
        int right=depth(node.left);
        return Math.max(left,right)+1;
    }

    /*
        链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/solution/dfsjian-dan-jie-ti-si-lu-yi-kan-jiu-dong-by-xiaora/
     */


}
