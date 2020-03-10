package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.TreeNode;

import java.util.*;

/**
 * Copyright gnuhpc 2019/12/14
 */
//DONE
public class TwoSumBSTs1214 {
    /*
    Method1 : 在遍历第二个树的时候由于中序遍历递增的特性，如果已经遍历到此时的元素加上最小的元素都大于target，
    只说明后边的更大，因此剪枝。 这只是一个优化，不加这个单set也行，实质更快且更节省内存。
     */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> list = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        prepare(root1, list, set);


        return isTrue(root2, list, set, target);
    }

    private boolean isTrue(TreeNode root, List<Integer> list, Set<Integer> set, int target) {
        if (root == null) return false;
        if (isTrue(root.left, list, set, target)) return true;
        if (set.contains(target - root.val)) return true;
        if (list.get(0) + root.val > target) return false;
        if (isTrue(root.right, list, set, target)) return true;

        return false;
    }

    private void prepare(TreeNode root, List<Integer> list, Set<Integer> set) {
        if (root == null) return;
        prepare(root.left, list, set);
        list.add(root.val);
        set.add(root.val);
        prepare(root.right, list, set);
    }

    /*
    Method2 : 更充分利用了BST进行递归查找
     */
    private boolean find(TreeNode root, int target) {
        if (root == null) {
            return false;
        } else if (root.val == target) {
            return true;
        } else if (root.val > target) {
            return find(root.left, target);
        } else {
            return find(root.right, target);
        }
    }

    public boolean twoSumBSTs2(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) {
            return false;
        } else if (find(root2, target - root1.val)) {
            return true;
        } else if (twoSumBSTs2(root1.left, root2, target)) {
            return true;
        } else {
            return twoSumBSTs2(root1.right, root2, target);
        }
    }
}
