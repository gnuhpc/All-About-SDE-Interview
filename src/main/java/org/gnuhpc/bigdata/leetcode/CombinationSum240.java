package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum240 {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    @Test
    public void test(){
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}
