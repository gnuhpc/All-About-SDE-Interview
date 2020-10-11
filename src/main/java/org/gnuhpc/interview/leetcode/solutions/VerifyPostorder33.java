package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/10
 */
public class VerifyPostorder33 {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) return false;
        if (postorder.length <= 2) return true;

        return helper(postorder, 0, postorder.length - 1);
    }

    private boolean helper(int[] postorder, int start, int end) {
        int len = end - start + 1;
        if (start >= end) return true;
        if (len == 1 || len == 2) return true;

        int i;
        for (i = start; i < end; i++) {
            if (postorder[i] > postorder[end]) break;
        }
        // 验证后面的是否都大于sequence[end]
        for (int j = i; j < end; j++) {
            if (postorder[j] < postorder[end]) return false;
        }
        return helper(postorder, start, i - 1) && helper(postorder, i, end - 1);
    }
}
