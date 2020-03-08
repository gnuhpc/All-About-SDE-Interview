package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class Subsets78 {
    /*
    Method1 : dfs
     */

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, int start) {

        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /*
    Method 2 : Robot ，重要方法
     */
    public boolean[]           selected;
    public List<List<Integer>> ans;

    public void robot(int idx, int[] nums) {

        if (idx >= nums.length) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                if (selected[i]) {
                    tmp.add(nums[i]);
                }
            }
            ans.add(tmp);
            return;
        }
        selected[idx] = true;
        robot(idx + 1, nums); //yes
        selected[idx] = false;
        robot(idx + 1, nums); //no
    }

    public List<List<Integer>> subsets2(int[] nums) {
        selected = new boolean[nums.length];
        ans = new ArrayList<>();
        robot(0, nums);
        return ans;
    }

    @Test
    public void test() {
        System.out.println(subsets2(new int[]{1, 2, 3}));
    }
}
