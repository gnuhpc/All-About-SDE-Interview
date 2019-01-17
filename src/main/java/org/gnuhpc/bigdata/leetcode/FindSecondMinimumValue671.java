package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.Set;
import java.util.TreeSet;

public class FindSecondMinimumValue671 {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        TreeSet<Integer> set = new TreeSet<>();
        dfs(root, set);

        Integer res = set.pollFirst();
        if (res == null) return -1;

        res = set.pollFirst();
        if (res!=null) return res;
        else return -1;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }
}
