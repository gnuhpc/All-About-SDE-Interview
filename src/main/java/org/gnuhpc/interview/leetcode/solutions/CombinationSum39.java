package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {
    /*
    Method 1:  标准dfs，遍历start，注意下一个start还是i
     */
    //target 为本次迭代后剩余的，start是起始idx
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, int target, int start) {
        if (target < 0 || start >= nums.length) return; //注意递归的结束条件
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(res, temp, nums, target - nums[i], i); // not i + 1 because we can reuse same elements
            temp.remove(temp.size() - 1);
        }
    }

    /*
    Method 2: robot方法
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        robot2(0, nums, target, new ArrayList<>(), res);
        return res;
    }

    private void robot2(int start, int[] nums, int target, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0 || start >= nums.length) return;
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        //选则添加
        temp.add(nums[start]);
        robot2(start, nums, target - nums[start], temp, res);

        //不选则还原
        temp.remove(temp.size() - 1);
        robot2(start + 1, nums, target, temp, res);
    }

    /*
    Method3: Robot 标记法
     */
    int[] flags;

    public List<List<Integer>> combinationSum3(int[] nums, int target) {
        flags = new int[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        robot3(0, nums, target, res);
        return res;
    }

    private void robot3(int start, int[] nums, int target, List<List<Integer>> res) {
        if (target < 0 || start == nums.length) return;
        if (target == 0) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < flags.length; i++) {
                for (int j = 0; j < flags[i]; j++) {
                    tmp.add(nums[i]);
                }
            }

            res.add(tmp);
            return;
        }

        flags[start]++;
        robot3(start, nums, target - nums[start], res);

        flags[start]--;
        robot3(start + 1, nums, target, res);
    }


    @Test
    public void test() {
        System.out.println(combinationSum3(new int[]{3, 2, 6, 7, 5}, 8));
        System.out.println(combinationSum(new int[]{3, 2, 1}, 6));
        System.out.println(combinationSum2(new int[]{3, 3, 3}, 9));

        long beforeTime = System.currentTimeMillis();
        combinationSum(new int[]{2, 3, 5, 6, 7, 8, 1, 9, 14}, 20);
        long afterTime = System.currentTimeMillis();
        long diffInMilliSeconds = afterTime - beforeTime;
        System.out.println(" Time cost is " + diffInMilliSeconds);

        beforeTime = System.currentTimeMillis();
        combinationSum(new int[]{2, 3, 5, 6, 7, 8, 1, 9, 14}, 20);
        afterTime = System.currentTimeMillis();
        diffInMilliSeconds = afterTime - beforeTime;
        System.out.println(" Time cost is " + diffInMilliSeconds);
    }

    // add by tina, 暴力递归
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        if (candidates == null || target < 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        genCombSum(candidates, target, 0, temp, res);
        return res;
    }


    public void genCombSum(int[] candidates, int target, int start, List<Integer> temp, List<List<Integer>> res) {

        if (sum(temp) == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (sum(temp) > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            genCombSum(candidates, target, i, temp, res);
            temp.remove(temp.get(temp.size() - 1));
        }

        return;
    }

    public int sum(List<Integer> temp) {
        int s = 0;
        for (int i = 0; i < temp.size(); i++) {
            s += temp.get(i);
        }
        return s;
    }
}
