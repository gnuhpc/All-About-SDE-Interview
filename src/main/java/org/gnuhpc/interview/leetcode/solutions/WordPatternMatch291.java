package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternMatch291 {
    //char - word
    Map<Character, String> map = new HashMap<>();
    //word
    Set<String> set = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.isEmpty()) return str.isEmpty();
        if (map.containsKey(pattern.charAt(0))) { //已经有这个pattern对应关系了，就检查符不符合
            String value = map.get(pattern.charAt(0));
            if (!str.startsWith(value))//不符合，则返回false
                return false;
            else //符合则继续递归下去
                return (wordPatternMatch(pattern.substring(1), str.substring(value.length())));
        } else { //还没有pattern
            for (int i = 1; i <= str.length(); i++) {
                //this word has been included already
                String word = str.substring(0, i);
                char c = pattern.charAt(0);
                if (set.contains(word)) continue;

                map.put(c, word);
                set.add(word);
                if (wordPatternMatch(
                        pattern.substring(1),
                        str.substring(i))) {
                    return true;
                }

                set.remove(word);
                map.remove(c);
            }
        }
        return false;
    }
}
