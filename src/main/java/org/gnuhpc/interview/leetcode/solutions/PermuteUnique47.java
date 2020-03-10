package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class PermuteUnique47 {

    //Method : DFS
/*
* 使用排列式深度优先搜索算法。
和没有重复元素的 Permutation 一题相比，只加了两句话：
Arrays.sort(nums) // 排序这样所有重复的数
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) { continue; } // 跳过会造成重复的情况
* */

    //https://www.youtube.com/watch?v=re9JDd7M-v8 上边有个图画的不错

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        boolean[] visited = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        dfs(nums, visited, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            //不能直接传进去list，否则对list的改动都体现在最终结果了
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // 当前元素与其前一个元素值相同 且 前元素尚没有被加到list中，跳过该元素 （如果不跳过直接加入，则不是用了第一个1就是用了第二个1，从结果看来都是重复的排列）
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;//only insert element when the previous duplicate element has been not inserted
            }
            // 深度优先搜索遍历
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, visited, list, res);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void test() {
        permuteUnique(new int[]{1, 1, 2, 3});
    }
}
