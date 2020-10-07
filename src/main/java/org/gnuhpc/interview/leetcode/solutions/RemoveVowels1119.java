package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/9/25
 */
public class RemoveVowels1119 {
    public String removeVowels(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'a' || S.charAt(i) == 'e' || S.charAt(i) == 'i' || S.charAt(i) == 'o' || S.charAt(i) == 'u')
                continue;
            sb.append(S.charAt(i));
        }
        return sb.toString();
    }

}
