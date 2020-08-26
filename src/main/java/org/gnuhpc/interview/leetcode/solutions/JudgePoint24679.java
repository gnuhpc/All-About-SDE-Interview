package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/11/4
 */
public class JudgePoint24679 {
    private final double EPS = 1e-2;

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length != 4) {
            return false;
        }

        double[] copy = new double[4];
        for (int i = 0; i < 4; i++) {
            copy[i] = nums[i];
        }

        return judgePoint24Helper(copy);
    }

    private boolean judgePoint24Helper(double[] nums) {
        int n = nums.length;
        if (n == 1) {
            return Math.abs(nums[0] - 24.0) <= EPS;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double num1 = nums[i];
                double num2 = nums[j];

                List<Double> ans = new ArrayList<>();
                ans.add(num1 + num2);
                ans.add(num1 - num2);
                ans.add(num2 - num1);
                ans.add(num1 * num2);
                ans.add(num1 / num2);
                ans.add(num2 / num1);

                for (Double num : ans) {
                    double[] tmp = new double[n - 1];
                    tmp[0] = num;
                    //构造下一个遍历的数组
                    int m = 1;
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j)
                            tmp[m++] = nums[k];
                    }

                    if (judgePoint24Helper(tmp)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
