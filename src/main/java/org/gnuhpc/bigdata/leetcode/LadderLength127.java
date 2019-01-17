package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;


public class LadderLength127 {
    /*
       Method1 : BFS 自己画图即可看出
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return 0;
        }
        boolean[] visited = new boolean[wordList.size()];
        if (wordList.indexOf(endWord) == -1) return 0;

        int res = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        res++;

        while (!queue.isEmpty()) {
            int size = queue.size();
            //那么在轮询的时候即按照这个大小来轮询
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String dictWord = wordList.get(j);
                    if (!visited[j] && diffOneChar(word, dictWord)) {
                        if (dictWord.equals(endWord)) return res + 1;
                        else {
                            visited[j] = true;
                            queue.offer(dictWord);
                        }
                    }
                }
            }
            res++;
        }

        return 0;
    }

    //字符长度相差1
    private boolean diffOneChar(String curr, String s) {
        int num = 0;
        for (int i = 0; i < curr.length(); i++) {
            if (curr.charAt(i) != s.charAt(i)) num++;
        }
        if (num == 1) return true;
        return false;
    }

    /*
    Method 2: BFS with steps in the node
     */
    HashMap<String, Integer> map = new HashMap<>();

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || beginWord.length() == 0 ||
                wordList.size() == 0 || beginWord.length() != endWord.length() ||
                wordList.indexOf(endWord) == -1)
            return 0;

        BFS(beginWord, endWord, wordList);
        return map.getOrDefault(endWord, -1) + 1;

    }

    private void BFS(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        map.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str.equals(endWord))
                continue;
            for (int i = 0; i < len; i++) {
                char[] word = str.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    word[i] = ch;
                    String newWord = new String(word);
                    if (wordSet.contains(newWord) &&
                            !map.containsKey(newWord)) {
                        map.put(newWord, map.get(str) + 1);
                        queue.add(newWord);
                        wordSet.remove(newWord);
                    }
                }
            }
        }
    }

    /*
    Method 3: Bidirectional BFS
    https://zxi.mytechroad.com/blog/searching/127-word-ladder/
     */

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) return 0;

        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);

        int len = beginWord.length();
        int steps = 0;

        while (!s1.isEmpty() && !s2.isEmpty()) {
            ++steps;

            //s1保存的集合较小，因此就遍历它
            if (s1.size() > s2.size()) {
                Set<String> tmp = s1;
                s1 = s2;
                s2 = tmp;
            }

            Set<String> tmpSet = new HashSet<>();

            for (String w : s1) {
                char[] chs = w.toCharArray();
                for (int i = 0; i < len; ++i) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chs[i] = c;
                        String newWord = new String(chs);
                        if (s2.contains(newWord)) return steps + 1;
                        if (!wordSet.contains(newWord)) continue;
                        wordSet.remove(newWord);
                        tmpSet.add(newWord);
                    }
                    chs[i] = ch;
                }
            }

            s1 = tmpSet;
        }
        return 0;
    }

    @Test
    public void test() {
        System.out.println(
                ladderLength2("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))
        );
    }

}
