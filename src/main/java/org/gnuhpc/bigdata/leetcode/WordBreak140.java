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
            max = Math.max(l, max);
            min = Math.min(l, min);
        }

        return doBreak(s, wordSet, 0);
    }


    Map<Integer, List<String>> memo = new HashMap<>();

    public List<String> doBreak(String s, Set<String> wordSet, int start) {

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");//返回空为最后处理结果提供方便
        }

        for (int end = start + min; end <= s.length(); end++) {
            String subStr = s.substring(start, end);
            if(isValid(subStr, wordSet)){
                List<String> list = doBreak(s, wordSet, end);
                for (String l : list) {
                    res.add(subStr + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        memo.put(start, res);
        return res;
    }

    private boolean isValid(String subStr, Set<String> wordSet) {
        boolean b1 = (subStr.length() <= max);
        boolean b2 = wordSet.contains(subStr);

        return b1 && b2;
    }


    @Test
    public void test() {
        String[] strs = new String[]{"cats", "dog", "sand", "and", "cat"};
        System.out.println(wordBreak("catsandog", Arrays.asList(strs)));
    }
}
