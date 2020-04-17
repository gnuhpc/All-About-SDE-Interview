package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/14
 */
//https://leetcode-cn.com/problems/candy/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-31/
public class Candy135 {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        //每人发一个糖
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        //正着进行
        for (int i = 0; i < n - 1; i++) {
            //当前小朋友的 rating 比后一个小朋友的小,后一个小朋友的糖是当前小朋友的糖加 1。
            if (ratings[i] < ratings[i + 1]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        //倒着进行
        //下标顺序就变成了 i i-1 i-2 i-3 ... 0
        //当前就是第 i 个，后一个就是第 i - 1 个
        for (int i = n - 1; i > 0; i--) {
            //当前小朋友的 rating 比后一个小朋友的小
            if (ratings[i] < ratings[i - 1]) {
                //后一个小朋友的糖果树没有前一个的多，就更新后一个等于前一个加 1
                if (candies[i - 1] <= candies[i]) {
                    candies[i - 1] = candies[i] + 1;
                }

            }
        }
        //计算糖果总和
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candies[i];
        }
        return sum;
    }
}
