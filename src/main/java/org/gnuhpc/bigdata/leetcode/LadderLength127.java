package org.gnuhpc.bigdata.leetcode;

import com.google.inject.internal.cglib.core.$Constants;
import org.junit.Test;

import java.util.*;


public class LadderLength127 {
    /*
       Method1 : BFS 自己画图即可看出
     */
    public int ladderLength(String start, String end, List<String> wordList) {
        if (start == null || start.length() == 0 ||
            wordList.size() == 0 || start.length() != end.length() ||
            !wordList.contains(end))
            return 0;

        Set<String> dict = new HashSet<>(wordList);

        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        int length = 1;
        while (!queue.isEmpty()) {
            length++; //注意BFS的层次记录
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, dict)) {
                    if (visited.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }

                    visited.add(nextWord);
                    queue.offer(nextWord);
                    dict.remove(word);
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
    private List<String> getNextWords(String word, Set<String> wordSet) {
        List<String> nextWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (wordSet.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }


    /*
    Method 2: BFS with steps in the node // TODO 学习这个记录distance 的方法,对于还原路径有作用
     */
    HashMap<String, Integer> wordStepMap = new HashMap<>();

    public int ladderLength2(String start, String end, List<String> wordList) {
        if (start == null || start.length() == 0 ||
            wordList.size() == 0 || start.length() != end.length() ||
            wordList.indexOf(end) == -1)
            return 0;

        BFS(start, end, wordList);
        return wordStepMap.getOrDefault(end, -1) + 1;

    }

    private void BFS(String start, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        wordStepMap.put(start, 0);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (word.equals(end))
                continue;
            for (String nextWord : getNextWords(word, wordSet)) {
                if (wordStepMap.containsKey(nextWord)) {
                    continue;
                }

                wordStepMap.put(nextWord, wordStepMap.get(word) + 1);
                queue.offer(nextWord);
                wordSet.remove(word);
            }
        }
    }

    /*
    Method 3: Bidirectional BFS //TODO 双向BFS , 由于每次都是从小的数据集开始中间凑,因此速度非常快!
    https://zxi.mytechroad.com/blog/searching/127-word-ladder/
     */

    public int ladderLength3(String start, String end, List<String> wordList) {
        if (start == null || start.length() == 0 ||
            wordList.size() == 0 || start.length() != end.length() ||
            wordList.indexOf(end) == -1)
            return 0;

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(end)) return 0;

        //这里用Queue也可以,区别不大,而且set会更快
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(start);
        s2.add(end);

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


            for (String word : s1) {
                for (String newWord : getNextWords(word, wordSet)) {
                    if (s2.contains(newWord)) {
                        return steps + 1;
                    }
                    wordSet.remove(word);
                    tmpSet.add(newWord);
                }
            }

            s1 = tmpSet;
        }
        return 0;
    }


    @Test
    public void test() {
        System.out.println(
                ladderLength2("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "aot", "aog"))
        );
    }

}
