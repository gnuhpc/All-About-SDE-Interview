package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2021/2/17
 */
public class FindDuplicateSubtrees652 {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) return res;
        help(root);
        return res;
    }

    public String help(TreeNode root) {
        if (root == null) return "#";

        String left = help(root.left);
        String right = help(root.right);
        //String s = left + "," + root.val + "," + right;
        String s = left + "," + right + "," + root.val;

        int freq = map.getOrDefault(s, 0);
        if (freq == 1) res.add(root);
        map.put(s, freq + 1);

        return s;

    }
}
