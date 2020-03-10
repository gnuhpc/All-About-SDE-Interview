package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, k, n, new ArrayList<>(), res);

        return res;
    }

    private void helper(int start, int size, int sum, List<Integer> tempList, List<List<Integer>> res) {
        if (size < 0 || sum < 0) return;
        if (size == 0 && sum == 0) {
            res.add(new ArrayList<>(tempList));
        }

        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            //注意size-1可以更加快速的通过给定数字个数已经用完而剪枝
            helper(i + 1, size - 1, sum - i, tempList, res);
            tempList.remove(tempList.size() - 1);
        }
    }


    @Test
    public void test() {
        combinationSum3(2, 6);
    }

    // add by Tina, 通用写法
    public List<List<Integer>> combinationSum32(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int[] nums = new int[9];
        for (int i = 1; i < 10; i++) {
            nums[i - 1] = i;
        }
        dfs(nums, k, n, 0, temp, res);
        return res;

    }

    public void dfs(int[] nums, int k, int target, int start, List<Integer> temp, List<List<Integer>> res) {
        if (target == 0 && k == temp.size()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (target < 0 || temp.size() >= k) return;
        // i < nums.length进行剪枝，例如，k=3,n=7
        // 第一个数字取8,后面只有一个数字，已经无法满足k=3要求了
        for (int i = start; i < nums.length - (k - temp.size()) + 1; i++) {
            //System.out.println("i="+i+", temp = " + temp);
            temp.add(nums[i]);
            dfs(nums, k, target - nums[i], i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
