package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/3/12
 */
public class GcdOfStrings1071 {
    /**
     * 思路：
     * 1、答案肯定是字符串的某个前缀，尝试枚举所有的前缀来判断
     * 2、前缀串的长度必然要是两个字符串长度的约数才能满足条件（l1 % l == 0 && l2 % l == 0）
     * 3、枚举符合长度条件的前缀串，再去判断这个前缀串拼接若干次以后是否等于 str1 和 str2 即可
     * 4、按长度从大到小枚举前缀串，这样碰到第一个满足条件的前缀串返回
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int min = Math.min(l1, l2);
        for (int l = min; l >= 1; l--) {
            if (l1 % l != 0 || l2 % l != 0) continue;
            String s = str1.substring(0, l);
            if (check(str1, s) && check(str2, s)) {
                return s;
            }
        }
        return "";
    }

    private boolean check(String target, String s) {
        int n = target.length() / s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return target.equals(builder.toString());
    }
}
