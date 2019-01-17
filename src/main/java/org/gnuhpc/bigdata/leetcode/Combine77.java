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

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums,i+1,size,temp,res);
            temp.remove(temp.size()-1);
        }

    }

    @Test
    public void test(){
        System.out.println(combine(4,2));
    }

}
