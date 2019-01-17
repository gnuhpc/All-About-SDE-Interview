package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, k, n, new ArrayList<>(), res);

        return res;

    }

    private void helper(int start, int size, int sum, List<Integer> tempList, List<List<Integer>> res) {
        if (tempList.size() > size || sum < 0) return;
        if (tempList.size() == size && sum == 0) {
            res.add(new ArrayList<>(tempList));
        }

        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            helper(i+1, size, sum-i, tempList,res);
            tempList.remove(tempList.size()-1);
        }
    }

    @Test
    public void test(){
        combinationSum3(2, 6);
    }
}
