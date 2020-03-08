package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.getNextWords;


public class LadderLength127 {
    /*
       Method1 : BFS 自己画图即可看出 , 最推荐
     */
    public int ladderLength(String start, String end, List<String> wordList) {
        if (start == null || start.length() == 0 ||
            wordList.size() == 0 || start.length() != end.length() ||
            !wordList.contains(end))
            return 0;

        Set<String> wordSet = new HashSet<>(wordList);

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
                for (String nextWord : getNextWords(word, wordSet)) {
                    if (visited.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }

                    visited.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }

        return 0;
    }


    /*
    Method 2: BFS with steps in the node //学习这个记录distance 的方法,对于还原路径有作用
     */
    HashMap<String, Integer> wordStepMap = new HashMap<>();

    public int ladderLength2(String start, String end, List<String> wordList) {
        if (start == null || start.length() == 0 ||
            wordList.size() == 0 || start.length() != end.length() ||
            wordList.indexOf(end) == -1)
            return 0;

        BFS(start, end, wordList);
        return wordStepMap.getOrDefault(end, 0);

    }

    private void BFS(String start, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        wordStepMap.put(start, 1);

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
    Method 3: Bidirectional BFS //双向BFS , 由于每次都是从小的数据集开始中间凑,因此速度非常快!
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
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(start);
        endSet.add(end);

        int steps = 1;

        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            ++steps;

            //s1保存的集合较小，因此就遍历它
            if (startSet.size() > endSet.size()) {
                Set<String> tmp = startSet;
                startSet = endSet;
                endSet = tmp;
            }

            Set<String> tmpSet = new HashSet<>();

            for (String word : startSet) {
                for (String newWord : getNextWords(word, wordSet)) {
                    if (endSet.contains(newWord)) {
                        return steps;
                    }
                    wordSet.remove(word);
                    tmpSet.add(newWord);
                }
            }

            startSet = tmpSet;
        }
        return 0;
    }

    /*
    Method4 : BFS 带状态
     */
    class WordNode {
        String word;
        int    numSteps;

        public WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }

    public int ladderLength4(String start, String end, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(end)) return 0;
        Queue<WordNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new WordNode(start, 1));
        visited.add(start);
        wordDict.add(end);

        while (!queue.isEmpty()) {
            WordNode top = queue.poll();
            String word = top.word;

            if (word.equals(end)) {
                return top.numSteps;
            }

            for (String newWord : getNextWords(word, wordDict)) {
                if (!visited.contains(newWord)) {
                    queue.add(new WordNode(newWord, top.numSteps + 1));
                    visited.add(newWord);
                }
            }
        }

        return 0;
    }

    @Test
    public void test() {
        System.out.println(
                ladderLength4("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "aot", "aog"))
        );
    }

}
