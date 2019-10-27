package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import sun.net.www.content.text.PlainTextInputStream;
import sun.nio.fs.GnomeFileTypeDetector;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class FindLeaves366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(root, res);

        return res;
    }

    private int getHeight(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;

        int height = 1 + Math.max(getHeight(root.left, res), getHeight(root.right, res));

        if (res.size() == height) { //Current height exceeds res list size
            res.add(new ArrayList<>());
        }

        res.get(height).add(root.val);

        return height;
    }
}
