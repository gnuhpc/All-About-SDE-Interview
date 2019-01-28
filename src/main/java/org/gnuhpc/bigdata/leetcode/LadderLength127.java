package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;


public class LadderLength127 {
    /*
       Method1 : BFS 自己画图即可看出
     */
    public int ladderLength(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }

        if (start.equals(end)) {
            return 1;
        }

        HashSet<String> hash = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        hash.add(start);

        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }

                    hash.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }

        return 0;
    }

    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
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
