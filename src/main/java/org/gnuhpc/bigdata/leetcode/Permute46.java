package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
问题模型：求出所有满足条件的“ 排列”。
判断条件：组合中的元素是顺序“ 相关”的。
时间复杂度：与 n! 相关。
 */
public class Permute46 {
    //方法一：交换递归 , 注意，这个方法对于重复的数字是无法剔除的，比如1,1,1,1
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        search(nums, 0, res);
        return res;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void search(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length - 1) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        // 将当前位置的数跟后面的数交换，并搜索解 ，然后回溯，交换回来
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            search(nums, start + 1, res);
            swap(nums, start, i);
        }
    }

    //方法二：backtracking + memory  最通用
    /*
    * Standard backtracking (dfs) approach
Key Idea: for each number, we need an additional array to indicate whether this number is used or not,
we can only use those unused numbers to generate the remaining part of the permutation.
    * */
    public List<List<Integer>> permute2(int[] nums) {

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
    1. 要遍历的集合
    2. 已经访问过的记录 (why？ 因为不能有重复的)
    3. 临时结果集
    4. 最终结果集

     */
    private void dfs(int[] nums,
                     boolean[] visited,
                     List<Integer> permutation,
                     List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }

    //方法三：迭代

    public List<List<Integer>> permute3(int[] nums) {
        ArrayList<List<Integer>> permutations = new ArrayList<>();
        if (nums == null) {
            return permutations;
        }

        if (nums.length == 0) {
            permutations.add(new ArrayList<>());
            return permutations;
        }

        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<>();

        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }

            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }
            permutations.add(permutation);
        }

        return permutations;
    }

    // 针对String有特殊的处理:字符串拼接
    /*
     * Sample method exposed to client to calculate permutation of String in Java. 对String的一些特殊处理
     */
    public void permutation(String input) {
        permutation("", input);
    }

    /*
     * Recursive method which actually prints all permutations
     * of given String, but since we are passing an empty String
     * as current permutation to start with,
     * I have made this method private and didn't exposed it to client.
     */
    private void permutation(String prefix, String word) {
        if (word.isEmpty()) {
            System.err.println(prefix);

        } else {
            for (int i = 0; i < word.length(); i++) {
                //取出i所在的char放在prefix里面，后边的word就去掉这个
                permutation(prefix + word.charAt(i), word.substring(0, i)
                        + word.substring(i + 1, word.length()));
            }
        }
    }

    @Test
    public void test() {
        System.out.println(permute3(new int[]{1, 2, 3}));
    }
}
