package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/10/20
 */
public class GetFactors254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3) return res;
        dfs(n, 2, res, new ArrayList<>());
        return res;

    }

    private void dfs(int n, int start, List<List<Integer>> res, List<Integer> temp) {
        if (n <= 1) {
            if (temp.size() > 1) {//get rid of itself
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                dfs(n / i, i, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }


    @Test
    public void test() {
        System.out.println(getFactors(12));
    }
}
