package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2021/1/30
 */
public class FlipMatchVoyage971 {
    int i = 0;        //记录voyage的位置
    boolean flag;    //记录树的行程与给定的行程 voyage 是否相匹配
    List<Integer> res = new ArrayList<>();

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        dfs(root, voyage);
        if (flag) {        //不匹配时，返回-1；
            List<Integer> ans = new ArrayList<>();
            ans.add(-1);
            return ans;
        }
        return res;
    }

    public void dfs(TreeNode root, int[] voyage) {
        if (root == null)                //为null，直接返回
            return;
        if (root.val != voyage[i]) {    //不匹配时，返回
            flag = true;
            return;
        }                            //左儿子不匹配时，二叉树翻转
        if (root.left != null && root.left.val != voyage[i + 1]) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            res.add(root.val);
        }
        i++;                        //前序
        dfs(root.left, voyage);
        dfs(root.right, voyage);
    }
}
