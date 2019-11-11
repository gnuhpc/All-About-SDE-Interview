package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

public class SmallestFromLeaf988 {
//    思路
//    DFS遍历所有的路径，遍历过程中将当前节点字符加到字符串的前面，获得从叶结点到根的字符串
//    获取的字符串与当前保留的最小字符串（若有）比较，更新最小字符串即可
//    Tip：使用 (char) ('a' + r.val) 对应 0-25 与 a-z 代码
    private String smallestStr = "";

    private void dfs(TreeNode r, String s) {
        s = (char) ('a' + r.val) + s;
        if (r.left == null && r.right == null) {
            // 若所生成字符串比当前最小字符串小，则更新保留
            if (smallestStr == "" || s.compareTo(smallestStr) < 0) {
                smallestStr = s;
            }
        } else {
            if (r.left != null) {
                dfs(r.left, s);
            }
            if (r.right != null) {
                dfs(r.right, s);
            }
        }
    }

    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }
        dfs(root, "");
        return smallestStr;
    }


}
