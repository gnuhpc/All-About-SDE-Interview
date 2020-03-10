package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RepeatedSubstringPattern459 {
    public boolean repeatedSubstringPattern(String s) {
        int sLength = s.length();
        List<String> prefixs = getPrefixs(s);
        for (String prefix : prefixs) {
            int i = 0;
            while (i <= sLength - prefix.length() && s.substring(i, i + prefix.length()).equals(prefix)) {
                i += prefix.length();
            }

            if (i == sLength) return true;
        }
        return false;
    }

    private List<String> getPrefixs(String s) {
        int sLength = s.length();
        List<String> res = new ArrayList<>();
        for (int i = 1; i < sLength; i++) {
            if (sLength % i != 0) continue;
            res.add(s.substring(0, i));
        }

        return res;
    }

    @Test
    public void test() {
        assertEquals(true, repeatedSubstringPattern("abab"));
        assertEquals(false, repeatedSubstringPattern("aba"));
    }
}
