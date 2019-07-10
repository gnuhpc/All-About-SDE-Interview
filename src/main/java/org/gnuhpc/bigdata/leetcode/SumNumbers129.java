package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Copyright gnuhpc 19-7-10
 */
public class SumNumbers129 {
    //Method 1: DFS + Backtracking
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;

        List<ArrayList<Integer>> allPaths = new ArrayList<>();

        //DFS
        getAllPaths(root, allPaths, new ArrayList<>());

        return getSum(allPaths);

    }

    private int getSum(List<ArrayList<Integer>> allPaths) {
        List<ArrayList<Integer>> reversed = allPaths.stream().peek(Collections::reverse).collect(Collectors.toList());

        return reversed.stream().map(l-> {
            int sum = 0;
            int factor = 1;
            for (int i = 0; i < l.size(); i++) {
                sum += l.get(i)*factor;
                factor *=10;
            }

            return sum;
        }).mapToInt(i->i).sum();

    }

    private void getAllPaths(TreeNode root, List<ArrayList<Integer>> allPaths, ArrayList<Integer> temp) {
        if (root == null) { return;}

        temp.add(root.val);
        if (root.left == null && root.right == null){
            allPaths.add(new ArrayList<>(temp));
            return;
        } else {
            if (root.left!=null) {
                getAllPaths(root.left, allPaths,temp);
                //回溯
                temp.remove(temp.size()-1);
            }

            if (root.right !=null){
                getAllPaths(root.right,allPaths,temp);
                //回溯
                temp.remove(temp.size()-1);
            }

        }

    }

    //Method 2: 纯DFS
    public int sumNumbers2(TreeNode root) {
        return helper(root, 0);
    }
    public int helper(TreeNode root, int sum){
        if(root == null) return 0;
        if(root.left == null && root.right == null)
            return root.val + sum*10;
        sum = root.val + sum*10;
        return helper(root.left, sum) + helper(root.right, sum);
    }


    //Method 3: BFS
    public int sumNumbers3(TreeNode root) {
        if (root == null) return 0;

        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

            if (temp.left == null && temp.right == null)
                sum += temp.val;

            int num = (temp.val * 10);

            if (temp.left != null) {
                temp.left.val = temp.left.val + num;
                q.offer (temp.left);
            }

            if (temp.right != null) {
                temp.right.val = temp.right.val + num;
                q.offer(temp.right);
            }

        }

        return sum;
    }

    @Test
    public void test(){

        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{4,9,0,5,1});
        assertEquals(1026,sumNumbers(root));

        TreeNode root2 = TreeCreator.createTreeByLevel(new Integer[]{1,null,5});
        assertEquals(15,sumNumbers(root2));
    }
}
