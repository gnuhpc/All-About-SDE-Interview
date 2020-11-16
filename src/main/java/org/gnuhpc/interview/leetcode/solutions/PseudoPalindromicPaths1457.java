package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

/**
 * Copyright gnuhpc 2020/11/8
 */
public class PseudoPalindromicPaths1457 {
    int ans;

    public int pseudoPalindromicPaths(TreeNode root) {
        ans = 0;
        if (root == null)
            return ans;
        dfs(root, new int[9]);
        return ans;
    }

    public void dfs(TreeNode root, int[] count) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            count[root.val - 1]++;
            if (check(count)) {
                ans++;
            }
            count[root.val - 1]--;
            return;
        }
        count[root.val - 1]++;
        dfs(root.left, count);
        dfs(root.right, count);
        count[root.val - 1]--;
        return;

    }

    public boolean check(int[] count) {
        int odd = 0;
        boolean flag = false;
        for (int i = 0; i < 9; ++i) {
            if (count[i] % 2 == 1)
                odd++;
            if (count[i] > 0)
                flag = true;
        }
        return flag && odd <= 1;
    }
}
