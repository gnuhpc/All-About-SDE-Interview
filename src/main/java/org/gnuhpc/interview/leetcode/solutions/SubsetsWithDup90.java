package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/*
思路同47
 */
public class SubsetsWithDup90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, List<Integer> temp, List<List<Integer>> res) {
        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            dfs(nums, i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    /*

    思路2： Robot
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        robot(nums, 0, new boolean[nums.length], res);
        return res;
    }

    private void robot(int[] nums, int start, boolean[] visited, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) tmp.add(nums[i]);
            }
            res.add(tmp);
            return;
        }

        //规则是能取前边的就取前边的
        //如果和前边的数字相同，且前边没有取，则后边也不取
        if (start > 0 && nums[start - 1] == nums[start] && !visited[start - 1]) {
            visited[start] = false;
            robot(nums, start + 1, visited, res);
        } else { //数字不同的情况下，前边取了，则后边这个可以取也可以不取
            visited[start] = true;
            robot(nums, start + 1, visited, res);

            visited[start] = false;
            robot(nums, start + 1, visited, res);
        }
    }

    @Test
    public void test() {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(subsetsWithDup2(new int[]{1, 2, 2}));
    }
}
