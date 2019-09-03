package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 19-8-28
 */
public class WordBreak140 {
    private int min = 0, max = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, List<String>> memo = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>(wordDict);

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (String word : wordDict) {
            int l = word.length();
            max = l > max ? l : max;
            min = l < min ? l : min;
        }

        return word_Break(s, wordSet, 0);
    }


    Map<Integer, List<String>> memo = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordSet, int start) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new LinkedList<>();

        for (int end = start + min; end <= s.length(); end++) {
            if (end - start > max) break;
            String subStr = s.substring(start, end);
            if (wordSet.contains(subStr)) {
                List<String> list = word_Break(s, wordSet, end);
                if (!list.isEmpty()) {
                    for (String l : list) {
                        res.add(subStr + " " + l);
                    }
                }
                else { // list 为空可能是两件事 ，如果是end到头了，则加入substr，如果是不匹配则substr不符合
                    if (end == s.length()) res.add(subStr);
                }
            }
        }
        memo.put(start, res);
        return res;
    }


    @Test
    public void test() {
        String[] strs = new String[]{"cats", "dog", "sand", "and", "cat"};
        System.out.println(wordBreak("catsandog", Arrays.asList(strs)));
    }
}
