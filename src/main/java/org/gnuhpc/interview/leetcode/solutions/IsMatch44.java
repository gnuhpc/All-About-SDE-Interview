package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

//https://longwayjade.wordpress.com/2015/04/26/leetcode-recursion-dp-greedy-wildcard-matching/
public class IsMatch44 {
    /*
    Method : recursion ，这种更好理解, 推荐
     */

    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        //遍历整个字符串
        while (s < str.length()) {
            // 一对一匹配，两指针同时后移。
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // 碰到 *，假设它匹配空串，并且用 startIdx 记录 * 的位置，记录当前字符串的位置，p 后移
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // 当前字符不匹配，并且也没有 *，回退
            // p 回到 * 的下一个位置
            // match 更新到下一个位置
            // s 回到更新后的 match
            // 这步代表用 * 匹配了一个字符
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
            //字符不匹配，也没有 *，返回 false
            else return false;
        }

        //将末尾多余的 * 直接匹配空串 例如 text = ab, pattern = a*******
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();

    }

    @Test
    public void test() {
//        System.out.println(isMatch("aa","a"));
//        System.out.println(isMatch("aa","*"));
//        System.out.println(isMatch("cb","?a"));
//        System.out.println(isMatch("adceb","*a*b"));
//        System.out.println(isMatch("acdcb","a*c?b"));
//        System.out.println(isMatch("abefcdgiescdfimde","ab*cd?i*de"));
//        System.out.println(isMatch("aaaab","**b"));
//        System.out.println(isMatch("adceb","*a*b"));
//        System.out.println(isMatch("c","*?*"));
//        System.out.println(isMatch("b","*?*?"));
//        System.out.println(isMatch("b","*?*?*"));
//        System.out.println(isMatch("hi","*?"));
        System.out.println(isMatch("aab", "*a"));
    }


}
