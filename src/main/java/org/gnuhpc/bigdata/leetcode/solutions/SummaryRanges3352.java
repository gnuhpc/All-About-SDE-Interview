package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Interval;
import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 19-8-4
 */

//BST， 太复杂了...
public class SummaryRanges3352 {
    class BSTNode {
        Interval interval;
        BSTNode  left;
        BSTNode  right;

        BSTNode(Interval in) {
            interval = in;
        }
    }

    BSTNode findMin(BSTNode root) {
        if (root == null) return null;
        if (root.left == null) return root;
        else return findMin(root.left);
    }

    BSTNode remove(Interval x, BSTNode root) {
        if (root == null) return null;
        else if (x == null) return root;
        else if (x.start > root.interval.end) {
            root.right = remove(x, root.right);
        }
        else if (x.end < root.interval.start) {
            root.left = remove(x, root.left);
        }
        else if (root.left != null && root.right != null) {
            root.interval = findMin(root.right).interval;
            root.right = remove(root.interval, root.right);
        }
        else {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }

    BSTNode findKey(int val, BSTNode root) {
        if (root == null) return null;
        if (root.interval.start > val) {
            return findKey(val, root.left);
        }
        else if (root.interval.end < val) {
            return findKey(val, root.right);
        }
        else return root;
    }

    BSTNode addKey(int val, BSTNode root) {
        if (root == null) {
            root = new BSTNode(new Interval(val, val));
        }
        else if (root.interval.start > val) {
            root.left = addKey(val, root.left);
        }
        else if (root.interval.end < val) {
            root.right = addKey(val, root.right);
        }
        return root;
    }

    void inOrder(BSTNode root) {
        if (root != null) {
            inOrder(root.left);
            list.add(root.interval);
            inOrder(root.right);
        }
    }

    /**
     * Initialize your data structure here.
     */
    BSTNode        root;
    List<Interval> list = new ArrayList<>();

    public SummaryRanges3352() {
        root = null;
    }

    public void addNum(int val) {
        if (root == null) {
            root = addKey(val, root);
        }
        else {
            if (findKey(val, root) != null) return;
            BSTNode left = findKey(val - 1, root);
            BSTNode right = findKey(val + 1, root);
            if (left == null && right == null) { //前不着村后不着店
                root = addKey(val, root);
            }
            else if (left != null && right == null) { //串接起前边的那个数组
                left.interval.end++;
            }
            else if (left == null && right != null) { //串接起后边的那个数组
                right.interval.start--;
            }
            else {//正好串接起前后两个数组
                Interval l = left.interval;
                int e = right.interval.end;
                root = remove(right.interval, root);
                l.end = e;
            }
        }
    }

    public int[][] getIntervals() {
        list.clear();
        inOrder(root); //中序遍历一次就是由大到小了。

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            int[] temp = new int[2];
            temp[0] = list.get(i).start;
            temp[1] = list.get(i).end;
            res[i] = temp;
        }

        return res;
    }


    @Test
    public void test() {
        SummaryRanges3352 summaryRanges = new SummaryRanges3352();
        addNum(1);
        addNum(3);
        addNum(7);
        addNum(2);
        addNum(6);

        Utils.print2DArray(getIntervals());
    }
}
