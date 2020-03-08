package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

//注意这个题意里面的*不是通配符，是0到多个前边的字符的意思
public class IsMatch10 {
    /*
    递归边界:当p为null 的时候，判断 s.isEmpty()
    递归函数:分为两个部分，一部分是当匹配串第二个元素不是 "*" 时候，此时递归函数为
        first && isMatch(s.substring(1), p.substring(1))
    当匹配串第二个元素是 "*" 的时候，
    此时分为直接匹配零个前面的元素 和 匹配多个前面的元素两种情况
        isMatch(s, p.substring(2)) || (first && isMatch(s.substring(1), p));
     */

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        boolean first = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            // Situation1: c-a*c
            // Situation2: abc - a*c -> bc - a*c
            return isMatch(s, p.substring(2)) || (first && isMatch(s.substring(1), p));
        }
        else {
            return first && isMatch(s.substring(1), p.substring(1));
        }
    }

    @Test
    public void test() {
        System.out.println(isMatch("c", "a*c"));
        System.out.println(isMatch("aaab", "a*b"));
        System.out.println(isMatch("aab", "a*aab"));
    }
}
