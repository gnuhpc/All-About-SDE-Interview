package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Copyright gnuhpc 2020/5/31
 */
public class GetAllElements1305 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        dfs(root1, l1);
        dfs(root2, l2);

        if (l1.size() == 0) return l2;
        else if (l2.size() == 0) return l1;
        else return merge(l1, l2);
    }

    private void dfs(TreeNode root1, List<Integer> ansList) {
        if (root1 == null) {
            return;
        }
        dfs(root1.left, ansList);
        ansList.add(root1.val);
        dfs(root1.right, ansList);
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> ansList = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int index1, index2;
        for (index1 = 0, index2 = 0; index1 < size1 && index2 < size2; ) {
            int num1 = list1.get(index1);
            int num2 = list2.get(index2);
            if (num1 < num2) {
                ansList.add(num1);
                index1++;
            } else {
                ansList.add(num2);
                index2++;
            }
        }

        while (index1 < size1) {
            ansList.add(list1.get(index1++));
        }

        while (index2 < size2) {
            ansList.add(list2.get(index2++));
        }

        return ansList;
    }

    /*
    Method2: 边遍历边添加，用到了一个写法，树的迭代器
     */

    private List<Integer> allElementsList;

    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        allElementsList = new ArrayList<>();

        TreeIterator tI1 = new TreeIterator(root1);
        TreeIterator tI2 = new TreeIterator(root2);
        TreeNode t1 = null;
        TreeNode t2 = null;

        while ((tI1.hasNext() || t1 != null) && (tI2.hasNext() || t2 != null)) {
            if (null == t1)
                t1 = tI1.next();
            if (null == t2)
                t2 = tI2.next();

            if (t1.val < t2.val) {
                allElementsList.add(t1.val);
                System.out.println("T1: " + t1.val);
                t1 = null;
            } else {
                allElementsList.add(t2.val);
                System.out.println("T2: " + t2.val);
                t2 = null;
            }
        }

        if (!tI1.hasNext() && !tI2.hasNext() && tI1 == null && tI2 == null)
            return allElementsList;

        TreeIterator lastIter = tI1.hasNext() ? tI1 : tI2;
        TreeNode lastNode = t1 == null ? t2 : t1;
        if (null != lastNode) {
            allElementsList.add(lastNode.val);
            System.out.println("LastNode: " + lastNode.val);
        }
        while (lastIter.hasNext()) {
            allElementsList.add(lastIter.next().val);
        }

        return allElementsList;
    }

    class TreeIterator implements Iterator<TreeNode> {
        //中序遍历迭代器

        private final Stack<TreeNode> stack;

        TreeIterator(TreeNode root) {
            stack = new Stack<>();
            pushLeftPath(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushLeftPath(TreeNode node) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public TreeNode next() {
            if (stack.isEmpty())
                return null;

            TreeNode ret = stack.pop();
            pushLeftPath(ret.right);
            return ret;
        }
    }
}
