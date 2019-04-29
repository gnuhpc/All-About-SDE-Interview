package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Combine77 {
    public List<List<Integer>> combine(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }
        return combine(arr,k);
    }


    public List<List<Integer>> combine(int[] nums, int k){
        List<List<Integer>> res = new ArrayList<>();
        if (k == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else{
            dfs(nums, 0,  k, new ArrayList<>(),res);
        }

        return res;
    }

    /*
    组合DFS套路：
    1. 原数据集
    2. 从哪里开始（因为无序不要重复，但是还要剔除已经用过的，因此要一直往前找，这个就是从哪里开始往前找的依据）
    3. 临时存放位置
    4. 最终结果集

     */

    private void dfs(int[] nums, int start, int size, List<Integer> temp, List<List<Integer>> res) {
        if (temp.size() == size) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < nums.length -(size-temp.size()) + 1; i++) { //nums.length
            temp.add(nums[i]);
            dfs(nums,i+1,size,temp,res);
            temp.remove(temp.size()-1);
        }

    }

    @Test
    public void test(){
        System.out.println(combine(4,2));
    }


    // add by @Tina
    // 注意这类题目中，dfs不用返回值，
    // 因为List是引用类型，从定义开始，到传参进去
    // 任何一次改变，都会被带回来
    // 注意回溯的过程，需要把刚刚这一轮加到temp中的元素remomve，
    // 继续遍历下一个
    // i=n时，已经没有元素可以加进list了，不必再进行递归，可进行剪枝
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        dfs(res, temp, n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int n, int k, int m) {
        if(temp.size() == k) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        /*or(int i=m; i<=n; i++) {
            temp.add(i);
            dfs(res, temp, n, k, i+1);
            temp.remove(temp.size()-1);
        }*/

        // 此时temp还剩下k-temp.size个位置，确保[i,...,n]中至少包含k-temp.size个元素
        // i <= n-(k-temp.size) +1
        for(int i=m; i<=n-(k-temp.size()) +1; i++) {
            temp.add(i);
            dfs(res, temp, n, k, i+1);
            temp.remove(temp.size()-1);
        }
    }




}
