package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {
    private static int counter = 0;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    //思路： 标准dfs，遍历start，注意下一个start还是i
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0 || start >= nums.length) return; //注意递归的结束条件
        if(remain == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            dfs(res, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }

    /*
    思路2， dfs 遍历法， 有个robot将所有情况跑到并且计入
    Faster method!!

    leetode运行发现没有方法一快啊！！！
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        robot(0,nums,target,new ArrayList<>(),res);

        return res;
    }

    private void robot(int idx, int[] nums, int target, List<Integer> temp, List<List<Integer>> res) {
        counter++;
        if (target == 0){
            res.add(new ArrayList<>(temp));
            return;
        }

        if (target < 0 || idx >= nums.length) return;

        //选
        temp.add(nums[idx]);
        robot(idx,nums, target-nums[idx], temp,res);

        //不选
        //还原
        temp.remove(temp.size()-1);
        robot(idx+1,nums, target, temp,res);
    }

    @Test
    public void test(){
        System.out.println(combinationSum(new int[]{3,2,5},8));
        System.out.println(counter);

        counter = 0;

        System.out.println(combinationSum2(new int[]{3,2,5},8));
        System.out.println(counter);


        long beforeTime = System.currentTimeMillis();
        combinationSum(new int[]{2,3,5,1,2,3,4,5,4,5,6,7,8,8,3,1,2,3,4,5,1,2,3,4,6,3,2,1},20);
        long afterTime = System.currentTimeMillis();
        long diffInMilliSeconds = afterTime- beforeTime;
        System.out.println(" Time cost is " + diffInMilliSeconds);

        beforeTime = System.currentTimeMillis();
        combinationSum2(new int[]{2,3,5,1,2,3,4,5,4,5,6,7,8,8,3,1,2,3,4,5,1,2,3,4,6,3,2,1},20);
        afterTime = System.currentTimeMillis();
        diffInMilliSeconds = afterTime- beforeTime;
        System.out.println(" Time cost is " + diffInMilliSeconds);
    }

    // add by tina, 暴力递归
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        if(candidates == null || target < 0) return null;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        genCombSum(candidates,target,0,temp,res);
        return res;
    }


    public void genCombSum(int[] candidates, int target, int start, List<Integer> temp,List<List<Integer>> res){

        if(sum(temp) == target) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        if(sum(temp) > target) {
            return;
        }

        for(int i = start; i < candidates.length; i++){
            temp.add(candidates[i]);
            genCombSum(candidates,target,i,temp,res);
            temp.remove(temp.get(temp.size() -1));
        }

        return;
    }

    public int sum(List<Integer> temp){
        int s = 0;
        for(int i  = 0; i< temp.size(); i++){
            s += temp.get(i);
        }
        return s;
    }
}
