package org.gnuhpc.interview.leetcode.solutions;

public class MaxSatisfied1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int[] temp = new int[customers.length];
        int total = 0;//不挽回的总满意量
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 1) temp[i] = customers[i];//损失了多少
            else total += customers[i];//添加到满意量
        }

        int save = 0, max = 0;//save：当前窗口内挽回的满意量， max最大挽回的满意量
        for (int r = 0; r < temp.length; r++) {
            if (r - X >= 0) {
                save -= temp[r - X];
            }
            save += temp[r];
            max = Math.max(max, save);
        }
        return total + max;
    }
}
