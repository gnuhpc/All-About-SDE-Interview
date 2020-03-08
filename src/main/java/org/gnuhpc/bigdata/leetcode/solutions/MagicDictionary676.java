package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MagicDictionary676 {
    /**
     * Initialize your data structure here.
     */
    private Map<Integer, List<String>> internalMap;

    public MagicDictionary676() {
        this.internalMap = new HashMap<>();
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            int len = s.length();
            if (internalMap.containsKey(len)) {
                internalMap.get(len).add(s);
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(s);
                internalMap.put(len, l);
            }
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        int len = word.length();
        if (!internalMap.containsKey(len)) return false;
        else return checkOneEdit(word, internalMap.get(len));
    }

    private boolean checkOneEdit(String word, List<String> strings) {
        for (String s : strings) {
            if (ifOneEdit(word, s)) return true;
        }

        return false;
    }

    private boolean ifOneEdit(String word, String str) {
        int dis = 0;
        char[] wordArray = word.toCharArray();
        char[] strArray = str.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (wordArray[i] != strArray[i]) {
                dis++;
            }
        }
        return dis == 1;
    }

    @Test
    public void test() {
        buildDict(new String[]{"hello", "leetcode"});
        assertFalse(search("hello"));
        assertTrue(search("hhllo"));
        assertFalse(search("hell"));
        assertFalse(search("leetcoded"));
    }
}

/*
还看到了一个记录缺哪个字母的,速度更快
class MagicDictionary {

    Map<String, List<int[]>> memo = new HashMap<>();
    public MagicDictionary() {
    }

    public void buildDict(String[] dict) {
        for (String s : dict) {
            for (int i = 0; i < s.length(); i++) {
                String key = s.substring(0, i) + s.substring(i + 1);
                int[] pair = new int[] {i, s.charAt(i)};

                List<int[]> val = memo.getOrDefault(key, new ArrayList<int[]>());
                val.add(pair);

                memo.put(key, val);
            }
        }
    }

    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + word.substring(i + 1);
            if (memo.containsKey(key)) {
                for (int[] pair : memo.get(key)) {
                    if (pair[0] == i && pair[1] != word.charAt(i)) return true;
                }
            }
        }
        return false;
    }
}


 */
