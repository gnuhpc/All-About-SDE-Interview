package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class FindLeaves366 {
    /*
    The key to solve this problem is converting the problem to be
    finding the index of the element in the result list.
    Then this is a typical DFS problem on trees.
     */

    Map<Integer, List<Integer>> depth = new HashMap<>();

    int maxDepth = 0;

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root);

        for (int i = 1; i < maxDepth; i++) {
            res.add(depth.get(i));
        }
        return res;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        int curHeight = Math.max(height(root.left), height(root.right)) + 1;

        maxDepth = Math.max(maxDepth, curHeight);

        if (!depth.containsKey(curHeight)) depth.put(curHeight, new ArrayList<>());

        depth.get(curHeight).add(root.val);

        return curHeight;
    }

}
