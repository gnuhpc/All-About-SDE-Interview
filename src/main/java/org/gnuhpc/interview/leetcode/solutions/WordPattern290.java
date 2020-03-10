package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class WordPattern290 {
    @Test
    public void test() {
        String pattern = "abba", str = "dog cat cat dog";
        System.out.println(wordPattern(pattern, str));
    }

    // 2个map，判断包含key的方法，一个map就用containsKey和containsValue
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) return false;
        String[] strArr = str.split(" ");
        char[] patternArr = pattern.toCharArray();
        if (patternArr.length != strArr.length) return false;

        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        for (int i = 0; i < strArr.length; i++) {
            if (map2.containsKey(strArr[i])) {
                Character c = map2.get(strArr[i]);
                if (c != patternArr[i]) return false;
            } else {
                if (map1.containsKey(patternArr[i]) && !map1.get(patternArr[i]).equals(strArr[i])) {
                    return false;
                }
                map1.put(patternArr[i], strArr[i]);
                map2.put(strArr[i], patternArr[i]);
            }
        }

        return true;
    }

}
