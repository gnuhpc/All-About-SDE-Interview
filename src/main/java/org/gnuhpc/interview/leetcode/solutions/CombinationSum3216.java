package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3216 {
    int k = 0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        List<List<Integer>> res = new ArrayList<>();
        helper(1, n, new ArrayList<>(), res);

        return res;
    }

    private void helper(int start, int sum, List<Integer> tempList, List<List<Integer>> res) {
        if (tempList.size()>k || sum < 0) return;
        if (tempList.size()== k && sum == 0) {
            res.add(new ArrayList<>(tempList));
        }

        //&&是根据还差几个数和还有几个数的比较来进行剪枝
        for (int i = start; i <= 9 && k - tempList.size() <= 9 - i +1; i++) {
            tempList.add(i);
            helper(i + 1,sum - i, tempList, res);
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
