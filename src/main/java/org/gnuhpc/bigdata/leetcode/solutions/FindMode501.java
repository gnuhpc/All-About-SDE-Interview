package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindMode501 {
    int   curval    = 0;
    int   curcount  = 0;
    int[] mode;
    int   maxcount  = 0;
    int   modecount = 0;

    public int[] findMode(TreeNode root) {
        inorder(root);
        mode = new int[modecount];
        curcount = 0;
        modecount = 0;
        inorder(root);
        return mode;
    }

    private void handleval(int val) {
        if (curval != val) {
            curval = val;
            curcount = 0;
        }
        curcount++;
        if (curcount > maxcount) {
            maxcount = curcount;
            modecount = 1;
        }
        else if (curcount == maxcount) {
            if (mode != null) {
                mode[modecount] = curval;
            }
            modecount++;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleval(root.val);
        inorder(root.right);
    }

    //Method 2
    private int           cnt     = 1;
    private int           maxCnt  = 1;
    private TreeNode      preNode = null;
    private List<Integer> list    = new ArrayList<>();

    public int[] findMode2(TreeNode root) {
        inOrderHelper(root);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void inOrderHelper(TreeNode node) {
        if (node == null) return;
        inOrderHelper(node.left);
        if (preNode != null) {
            if (preNode.val == node.val) cnt++;
            else cnt = 1;
        }

        if (cnt > maxCnt) {
            maxCnt = cnt;
            list.clear();
            list.add(node.val);
        }
        else if (cnt == maxCnt) {
            list.add(node.val);
        }

        preNode = node;
        inOrderHelper(node.right);
    }
}
