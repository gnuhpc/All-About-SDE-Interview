package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class IsScramble87 {
    public boolean isScramble(String s1, String s2) {
        Map<String, Integer> memoization = new HashMap<>();
        return isScrambleRecursion(s1, s2, memoization);
    }

    public boolean isScrambleRecursion(String s1, String s2, Map<String, Integer> memoization) {
        //判断之前是否已经有了结果
        int ret = memoization.getOrDefault(s1 + "#" + s2, -1);
        if (ret == 1) {
            return true;
        }
        else if (ret == 0) {
            return false;
        }
        if (s1.length() != s2.length()) {
            memoization.put(s1 + "#" + s2, 0);
            return false;
        }
        if (s1.equals(s2)) {
            memoization.put(s1 + "#" + s2, 1);
            return true;
        }

        int[] letters = new int[26]; //统计词频，不一致的也返回，返回前先记录
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++)
            if (letters[i] != 0) {
                memoization.put(s1 + "#" + s2, 0);
                return false;
            }

        //遍历每个切割位置
        for (int i = 1; i < s1.length(); i++) {
            //对应情况 1 ，判断 S1 的子树能否变为 S2 相应部分
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memoization.put(s1 + "#" + s2, 1);
                return true;
            }
            //对应情况 2 ，S1 两个子树先进行了交换，然后判断 S1 的子树能否变为 S2 相应部分
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                memoization.put(s1 + "#" + s2, 1);
                return true;
            }
        }
        memoization.put(s1 + "#" + s2, 0);
        return false;
    }

    // add by tina,不加memo更快

    /**
     * If string s1 and s2 are scramble strings, there must be a point that breaks s1 to two parts s11, s12, and a point that breaks s2 to two parts, s21, s22, and isScramble(s11, s21) && isScramble(s12, s22) is true, or isScramble(s11, s22) && isScramble(s12, s21) is true.
     * So we can make it recursively. We just break s1 at different position to check if there exists one position satisfies the requirement.
     * Some checks are needed otherwise it will time out. For example, if the lengths of two strings are different, they can’t be scramble. And if the characters in two strings are different, they can’t be scramble either.
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble2(String s1, String s2) {
        //Check lengths.
        if (s1.length() != s2.length())
            return false;
        if (s1.equals(s2))
            return true;

        int L = s1.length();
        //Check characters.
        int[] chars = new int[26];
        for (int i = 0; i < L; i++) {
            chars[s1.charAt(i) - 'a']++;
            chars[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0)
                return false;
        }

        //More letters
        for (int i = 1; i < L; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, L);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, L);
            if (isScramble(s11, s21) && isScramble(s12, s22))
                return true;
            s21 = s2.substring(0, L - i);
            s22 = s2.substring(L - i, L);
            if (isScramble(s11, s22) && isScramble(s12, s21))
                return true;
        }
        return false;
    }
}
