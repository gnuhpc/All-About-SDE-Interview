package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 此处需要注意组合去重，例如
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 和lc47对比下
 */
public class CombinationSum240 {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {

                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

        /**
         * 参考47题的写法，没有上面的for快
         *
         for(int i = start; i < candidates.length  ; i++){
         if( i > 0  && candidates[i] == candidates[i-1] && !used[i-1]){
         continue;
         }
         used[i] = true;
         temp.add(candidates[i]);
         dfs(candidates,target-candidates[i], i+1,used,temp,res);
         temp.remove(temp.get(temp.size()-1));
         used[i] = false;
         }
         **/
    }


    @Test
    public void test() {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
}
