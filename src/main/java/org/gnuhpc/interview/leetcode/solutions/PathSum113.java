package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSum113 {

    /*
    Method : DFS + backtracking
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();

        helper(root, sum, ans, new ArrayList<>());

        return ans;
    }

    private void helper(TreeNode node, int sum, List<List<Integer>> ans, List<Integer> path) {
        if (node == null) {
            return;
        }

        // 将沿途到节点加入到path中
        sum -= node.val;
        path.add(node.val);

        // 遍历到叶节点
        if (node.left == null && node.right == null) {
            // 如果这是一条可行的路径，才复制path的结果到ans
            if (sum == 0)
                ans.add(new ArrayList<>(path));
        } else {
            if (node.left != null) helper(node.left, sum, ans, path);
            if (node.right != null) helper(node.right, sum, ans, path);
        }

        // 注意，此时左右子节点都做完了，就要开始向上回溯了，这个节点也可以退出遍历过程了！ 回溯！！
        path.remove(path.size() - 1);
    }

    //不实用，太容易写错了
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        int SUM = 0;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                path.add(cur.val);
                SUM += cur.val;
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }
            if (cur.left == null && cur.right == null && SUM == sum)
                res.add(new ArrayList<>(path));

            pre = cur;
            //回溯
            stack.pop();
            path.remove(path.size() - 1);
            SUM -= cur.val;
            cur = null;

        }
        return res;
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        System.out.println(pathSum(root, 22));
    }
}
