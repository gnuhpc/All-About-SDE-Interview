package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
问题模型：求出所有满足条件的“ 排列”。
判断条件：组合中的元素是顺序“ 相关”的。
时间复杂度：与 n! 相关。
 */
public class Permute46 {

    //方法：backtracking + memory  最通用
    /*
    * Standard backtracking (dfs) approach
Key Idea: for each number, we need an additional array to indicate whether this number is used or not,
we can only use those unused numbers to generate the remaining part of the permutation.
    * */
    public List<List<Integer>> permute(int[] nums) {

        /*
         * @param nums: Sample list of integers.
         * @return: Sample list of permutations.
         */
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }

        dfs(nums, new boolean[nums.length], new ArrayList<>(), results);

        return results;
    }

    /*
    排列DFS套路，传入参数：
    1. 原数据集
    2. 已经访问过的记录 (why？ 因为不能有重复的) 这是和组合不一样的套路
    3. 临时结果集
    4. 最终结果集

    函数内的处理逻辑：
    1. 判断退出条件
    2. 遍历每个数字，选择过的跳过，否则加入临时结果集。然后递归进入，传入的内容一样。
    3. 然后回溯，将标记去掉，并从临时结果集中去掉最后的那个元素

     */
    private void dfs(int[] nums,
                     boolean[] visited,
                     List<Integer> temp,
                     List<List<Integer>> results) {
        if (nums.length == temp.size()) {
            results.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                temp.add(nums[i]);
                visited[i] = true;
                dfs(nums, visited, temp, results);
                visited[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    @Test
    public void test() {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
