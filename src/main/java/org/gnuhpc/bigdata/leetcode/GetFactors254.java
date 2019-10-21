package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/10/20
 */
public class GetFactors254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2) {
            return res;
        }
        helper(n, 2, new ArrayList<>(), res);
        return res;
    }

    public void helper(int n, int start, List<Integer> tmp, List<List<Integer>> res) {
        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                tmp.add(i);
                tmp.add(n / i);
                res.add(new ArrayList<Integer>(tmp)); //TODO 注意这种DFS的套路
                tmp.remove(tmp.size() - 1);
                helper(n / i, i, tmp, res);
                tmp.remove(tmp.size() - 1);
            }
        }
    }


    @Test
    public void test() {
        System.out.println(getFactors(12));
    }
}
