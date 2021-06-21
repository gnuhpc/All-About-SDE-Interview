package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class IsScramble87 {
    Map<String, Boolean> memo = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        String s = sb.append(s1).append(s2).toString();
        if (memo.get(s) != null) return memo.get(s);
        if (s1.equals(s2)) {
            memo.put(s, true);
            return true;
        }
        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            if ((isScramble(s11, s2.substring(0, i))
                    && isScramble(s12, s2.substring(i)))
                    || (isScramble(s11, s2.substring(s2.length() - i))
                    && isScramble(s12, s2.substring(0, s2.length() - i)))) {
                memo.put(s, true);
                return true;
            }

        }

        memo.put(s, false);
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
