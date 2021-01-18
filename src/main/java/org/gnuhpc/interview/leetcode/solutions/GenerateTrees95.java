package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/cong-gou-jian-dan-ke-shu-dao-gou-jian-suo-you-shu-/
public class GenerateTrees95 {

    //递归的方法
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateBST(1, n);
    }

    private List<TreeNode> generateBST(int lo, int hi) {
        List<TreeNode> ret = new ArrayList<>();
        if (lo > hi) {
            ret.add(null);
            return ret;
        }

        //以i为root
        for (int i = lo; i <= hi; ++i) {
            for (TreeNode left : generateBST(lo, i - 1)) {
                for (TreeNode right : generateBST(i + 1, hi)) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ret.add(root);
                }
            }
        }
        return ret;
    }

    @Test
    public void test() {
        generateTrees(5);
    }

}
