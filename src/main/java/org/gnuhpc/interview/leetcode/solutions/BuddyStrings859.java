package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class BuddyStrings859 {
    /*

分情况讨论：A和B长度不等、A和B内容相等、A和B内容不等
若A、B长度不同，返回 false;
若A、B内容相同，则统计A中是否有重复字符，若有，则可交换相同字符，使A不变，返回 true；
若A、B内容不等，统计A和B字符不等的位置，判断个数是否为2，是否在对应交换的位置上字符相等。

     */
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.equals(B)) {
            Set<Character> set = new HashSet<>();
            for (char c : A.toCharArray())
                set.add(c);
            return set.size() < A.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); i++)
            if (A.charAt(i) != B.charAt(i)) dif.add(i);
        return dif.size() == 2 &&
                A.charAt(dif.get(0)) == B.charAt(dif.get(1)) &&
                A.charAt(dif.get(1)) == B.charAt(dif.get(0));

    }
}
