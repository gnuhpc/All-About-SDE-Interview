package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrayCode89 {
    public List<Integer> grayCode(int n) {
        /*
        以3位为例
        000
        001
        011
        010
        110
        111
        101
        100
        可以看出前四个 是0 加上  2位格雷码组
        后四个是  pow(2,n-1)加上  2为格雷码组的倒叙排列
        因此可以使用递归
        */
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 0 || n == 1) {
            for (int k = 0; k <= n; k++)
                ans.add(k);
            return ans;
        }
        List<Integer> tmp = grayCode(n - 1);
        ans.addAll(tmp);
        Collections.reverse(tmp);
        for (Integer i : tmp) {
            ans.add((int) (Math.pow(2, n - 1) + i));
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(grayCode(2));
    }
}
