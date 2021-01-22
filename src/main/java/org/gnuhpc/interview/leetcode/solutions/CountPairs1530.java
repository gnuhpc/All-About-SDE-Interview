package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/12/26
 */
public class CountPairs1530 {
    int count = 0;

    public int countPairs(TreeNode root, int distance) {

        dfs(root, distance);
        return count;

    }

    public List<Integer> dfs(TreeNode root, int distance) {
        if (root == null) {
            return null;
        }
        List<Integer> leftList;
        List<Integer> rightList;
        List<Integer> list = new ArrayList<>();
        leftList = dfs(root.left, distance);
        rightList = dfs(root.right, distance);
        if (leftList == null && rightList == null) {
            list.add(1);
        } else {
            if (leftList != null && rightList != null) {
                for (int dis : leftList) {

                    for (int dis2 : rightList) {

                        if (dis + dis2 <= distance) {
                            count++;
                        }
                    }
                }
                leftList.forEach(x -> list.add(x + 1));
                rightList.forEach(x -> list.add(x + 1));
            } else {
                if (leftList != null) {
                    leftList.forEach(x -> list.add(x + 1));
                } else {
                    rightList.forEach(x -> list.add(x + 1));
                }
            }
        }
//  System.out.println("list.size=="+list.size());
        return list;
    }
}
