package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSubsequences491 {
    /*
    Method 1: 利用大Set去重的DFS
     */
    Set<List<Integer>> res;
    int[] nums;
    int n;
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        this.n = n;
        res = new HashSet<>();
        helper(new ArrayList<Integer>(), 0);
        return new ArrayList(res);
    }

    void helper(List<Integer> cur, int start) {
        if (cur.size() >= 2) {
            res.add(new ArrayList<Integer>(cur));
        }
        for (int i = start; i < nums.length; i ++) {
            if (cur.size() == 0 || cur.get(cur.size() - 1) <= nums[i]) {
                cur.add(nums[i]);
                helper(cur, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }

    /*
    Method2: 内部set，记录用过的元素，即使回溯掉了，但是用过的不再用了。
     */

    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, int cur){
        if(temp.size() > 1) {
            //System.out.println(temp);
            res.add(new ArrayList<>(temp));
        }
        //System.out.println(cur);
        if(cur == nums.length) return;


        Set<Integer> set = new HashSet<>();
        for(int i = cur; i < nums.length; i++){
            if(set.contains(nums[i])) continue;
            if(temp.size() == 0 || nums[i] >= temp.get(temp.size() - 1)){
                set.add(nums[i]);

                temp.add(nums[i]);
                dfs(res, temp, nums, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }


}
