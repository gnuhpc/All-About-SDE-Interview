package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.trie.Trie;
import org.junit.Test;

import java.util.*;

public class WordBreak139 {
    /*
    Method 1: dfs Brute force
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        return canBrk(0, s, wordSet);
    }

    boolean canBrk(int start, String s, Set<String> wordSet) {
        if (start == s.length()) return true;
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end))
                    && canBrk(end, s, wordSet))
                return true;
        }
        return false;
    }

    /*
    Method 2: dfs + Memorization
     */

    public boolean wordBreak2(String s, List<String> wordDict) {
        int[] cache = new int[s.length() + 1];
        Arrays.fill(cache, -1);
        Set<String> set = new HashSet<>(wordDict);
        return dfs(0, s, set, cache);
    }

    // -1 -- init 0 -- true 1-- false
    private boolean dfs(int start, String s, Set<String> wordDict, int[] cache) {
        if (start == s.length()) {
            cache[start] = 1;
            return true;
        }

        if (cache[start] != -1) return cache[start] == 1;

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (!wordDict.contains(word)) continue;
            if (dfs(end, s, wordDict, cache)) {
                cache[start] = 1;
                return true;
            }
        }

        cache[start] = 0;
        return false;
    }

    /*
    Method 3: 暴力
     */

    public boolean wordBreak3(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dict = new boolean[len + 1];

        dict[0] = true;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (dict[i] && wordDict.contains(s.substring(i, j))) {
                    dict[j] = true;
                }
            }
        }

        return dict[len];
    }

    /*
    Method 4: Trie
     */

    public boolean wordBreak4(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }
        HashMap<String, Boolean> memo = new HashMap<>();
        return canBreak(s, trie, memo);
    }

    private boolean canBreak(String s, Trie trie, HashMap<String, Boolean> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        for (int i = 1; i <= s.length(); i++) {
            if (trie.startsWith(s.substring(0, i))) {
                if (trie.search(s.substring(0, i))) {
                    if (canBreak(s.substring(i, s.length()), trie, memo)) {
                        memo.put(s, true);
                        return true;
                    }
                }
                if (i == s.length()) {
                    if (!trie.search(s)) {
                        memo.put(s, false);
                        return false;
                    }
                }
            }
            else {
                memo.put(s, false);
                return false;
            }
        }
        memo.put(s, true);
        return true;
    }

    /*
    Method 5: bfs
     */

    public boolean wordBreak5(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        Set<String> visited = new HashSet<>();
        while(!q.isEmpty()){
            String candidate = q.poll();
            if(wordSet.contains(candidate)) return true;
            for(int i = 0; i < candidate.length(); i++){
                String chop = candidate.substring(0,i);
                String next = candidate.substring(i);
                if(!visited.contains(next) && wordSet.contains(chop)){
                    q.offer(next);
                    visited.add(next);
                }
            }
        }
        return false;
    }

    /*
    Method 6: bfs 2
     */

    public boolean wordBreak6(String s, List<String> dict) {
        if (dict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        // use a set to record checked index to avoid repeated work.
        // This is the key to reduce the running time to O(N^2).
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            for (int i = curIdx+1; i <= s.length(); i++) {
                if (visited.contains(i)) continue;
                if (dict.contains(s.substring(curIdx, i))) {
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }



    @Test
    public void test() {
        System.out.println(wordBreak6("leetcodea", Arrays.asList("leet","code")));
    }
}
