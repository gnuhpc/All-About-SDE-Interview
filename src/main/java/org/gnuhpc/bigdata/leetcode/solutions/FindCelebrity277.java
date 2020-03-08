package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

/**
 * Copyright gnuhpc 2019/10/9
 */
public class FindCelebrity277 {
    /*
    Method1 :遍历法
     */
    private boolean[] celebrities;

    public int findCelebrity(int n) {
        celebrities = new boolean[n];
        Arrays.fill(celebrities, true);
        int count = n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (celebrities[j] && knows(j, i)) {
                    celebrities[j] = false;
                    count--;
                }

                if (celebrities[i] && knows(i, j)) {
                    celebrities[i] = false;
                    count--;
                }
                if (count == 0) return -1;
            }
            if (count == 0) return -1;
        }


        if (count != 1) {
            return -1;
        }// 检验
        else {
            for (int i = 0; i < celebrities.length; i++) {
                if (celebrities[i]) {
                    for (int j = 0; j < n; j++) {
                        if (i != j && !knows(j, i)) return -1;
                    }

                    return i;
                }
            }
        }


        return -1;
    }

    /*
    Method 2: 假设目标法 和Longest Mountain一样
     */

    /* 由题意知，名人是其他所有人都认识他/她（这个所有人中包括名人自己），而他/她并不认识其他任何人。
     * 所以我们可以固定一个人作为名人，然后判定它与其他人的关系，如果满足“所有人认识他而他不认识其他人”，则累加
     * 如果累加的人数为n，则表示此人为名人
     * 注意这里的累加算上了本人*/


    public int findCelebrity1(int n) {
        for (int celebrity = 0; celebrity < n; celebrity++) {
            int count = 0, others = 0;
            while ((others == celebrity) || (knows(others, celebrity) && !knows(celebrity, others)) && others < n) {
                //满足条件，累加
                count++;
                //防止数组越界
                if (count == n) return celebrity;
                others++;
            }
        }
        return -1;
    }


    private boolean knows(int a, int b) {
        return true;
    }
}
