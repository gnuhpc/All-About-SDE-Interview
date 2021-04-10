package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/2/18
 */
public class ShortestToChar821 {
    public int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
//        先找到前两个出现的位置
        int l = S.indexOf(C), r = S.indexOf(C, l + 1);
        for (int i = 0; i < S.length(); i++) {
//            计算与左指针的距离
            result[i] = Math.abs(l - i);
            if (r != -1) {
//                如果右指针存在,取较小的距离
                result[i] = Math.min(result[i], r - i);
//                走到右指针则左右指针往下一个
                if (i == r) {
                    result[i] = 0;
                    l = r;
                    r = S.indexOf(C, r + 1);
                }
            }
        }
        return result;
    }
}
