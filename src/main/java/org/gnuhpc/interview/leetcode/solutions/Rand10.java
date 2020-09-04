package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/2
 */
public class Rand10 {
    private int rand7() {
        return 0;
    }

    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7(); // 等概率生成[1,49]范围的随机数
            if (num <= 40) return num % 10 + 1; // 拒绝采样，并返回[1,10]范围的随机数
        }
    }
}
