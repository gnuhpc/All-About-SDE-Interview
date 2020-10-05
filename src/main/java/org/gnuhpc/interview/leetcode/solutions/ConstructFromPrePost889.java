package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class ConstructFromPrePost889 {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (N == 1) return root;

        int i = 1, j = 0;
        Set<Integer> tmpSet = new HashSet<>();
        for (; i < N && j < N; ++i, ++j) {
            if (!tmpSet.contains(pre[i])) {
                tmpSet.add(pre[i]);
            } else {
                tmpSet.remove(pre[i]);
            }

            if (!tmpSet.contains(post[j])) {
                tmpSet.add(post[j]);
            } else {
                tmpSet.remove(post[j]);
            }

            if (tmpSet.size() == 0) break;
        }

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, i + 1),
                Arrays.copyOfRange(post, 0, j + 1));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, i + 1, N),
                Arrays.copyOfRange(post, j + 1, N - 1));
        return root;
    }
}
